package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.ihelin.seven.common.dto.MemberRsepVo;
import io.github.ihelin.seven.common.dto.mq.OrderTo;
import io.github.ihelin.seven.common.dto.mq.SecKillOrderTo;
import io.github.ihelin.seven.common.enums.OrderStatusEnum;
import io.github.ihelin.seven.common.exception.NoStockException;
import io.github.ihelin.seven.common.utils.OrderConstant;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.dao.OrderDao;
import io.github.ihelin.seven.order.entity.OrderEntity;
import io.github.ihelin.seven.order.entity.OrderItemEntity;
import io.github.ihelin.seven.order.entity.PaymentInfoEntity;
import io.github.ihelin.seven.order.feign.MemberFeign;
import io.github.ihelin.seven.order.feign.ProductFeign;
import io.github.ihelin.seven.order.feign.WareFeign;
import io.github.ihelin.seven.order.interceptor.LoginUserInterceptor;
import io.github.ihelin.seven.order.service.OrderItemService;
import io.github.ihelin.seven.order.service.OrderService;
import io.github.ihelin.seven.order.service.PaymentInfoService;
import io.github.ihelin.seven.order.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * oms_order
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ThreadPoolExecutor executor;
    @Autowired
    private MemberFeign memberFeign;
    @Autowired
    private WareFeign wareFeign;
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private PaymentInfoService paymentInfoService;

    @Value("${myRabbitmq.MQConfig.orderEventExchange}")
    private String orderEventExchange;

    @Value("${myRabbitmq.MQConfig.createOrderRoutingKey}")
    private String createOrderRoutingKey;

    @Value("${myRabbitmq.MQConfig.ReleaseOtherKey}")
    private String ReleaseOtherKey;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
            new Query<OrderEntity>().getPage(params),
            new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        MemberRsepVo memberRsepVo = LoginUserInterceptor.THREAD_LOCAL.get();
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();

        // 从主线程获取用户数据，异步线程来共享
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
            // 异步线程共享 RequestContextHolder.getRequestAttributes()
            RequestContextHolder.setRequestAttributes(attributes);
            // 1.远程查询收获地址列表
            try {
                List<MemberAddressVo> address = memberFeign.getAddress(memberRsepVo.getId());
                orderConfirmVo.setAddress(address);
            } catch (Exception e) {
                logger.warn("远程调用会员服务失败 [会员服务可能未启动]");
            }
        }, executor);

        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            // 异步线程共享 RequestContextHolder.getRequestAttributes()
            RequestContextHolder.setRequestAttributes(attributes);
            // 2. 远程查询购物车列表
            List<OrderItemVo> items = productFeign.getCurrentUserCartItems();
            orderConfirmVo.setItems(items);
        }, executor).thenRunAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            List<OrderItemVo> items = orderConfirmVo.getItems();
            // 获取所有商品的id
            List<Long> skuIds = items.stream().map(OrderItemVo::getSkuId).collect(Collectors.toList());
            R hasStock = wareFeign.getSkuHasStock(skuIds);
            List<SkuStockVo> skuStockVos = hasStock.getData(new TypeReference<List<SkuStockVo>>() {
            });
            if (skuStockVos != null) {
                // 各个商品id 与 他们库存状态的映射
                Map<Long, Boolean> stocks = skuStockVos.stream().collect(Collectors.toMap(SkuStockVo::getSkuId, SkuStockVo::getHasStock));
                orderConfirmVo.setStocks(stocks);
            }
        }, executor);
        // 3.获取用户积分
        Integer integration = memberRsepVo.getIntegration();
        orderConfirmVo.setIntegration(integration);

        // 4.其他数据在类内部自动计算
        // 5.防重令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        orderConfirmVo.setOrderToken(token);
        stringRedisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberRsepVo.getId(), token, 10, TimeUnit.MINUTES);
        CompletableFuture.allOf(addressFuture, cartFuture).get();
        return orderConfirmVo;
    }

    /**
     * @param orderSubmitVo
     * @return
     * @GlobalTransactional AT模式 在高并发场景不适合
     * 高并发场景使用柔性事务，即可靠消息+最终一致性方案（异步确保型）
     */
//    @GlobalTransactional
    @Override
    @Transactional
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo orderSubmitVo) {
        Long addrId = orderSubmitVo.getAddrId();
        SubmitOrderResponseVo responseVo = new SubmitOrderResponseVo();
        // 0：正常
        responseVo.setCode(0);
        // 去服务器创建订单,验令牌,验价格,所库存
        MemberRsepVo memberRsepVo = LoginUserInterceptor.THREAD_LOCAL.get();
        String orderToken = orderSubmitVo.getOrderToken();
        // 1. 验证令牌 [必须保证原子性] 返回 0 or 1,0 令牌删除失败 1删除成功
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        // 原子验证令牌 删除令牌
        Long result = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
            Collections.singletonList(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberRsepVo.getId()), orderToken);
        if (result == 0L) {
            // 令牌验证失败
            responseVo.setCode(1);
        } else {
            // 令牌验证成功
            // 1 .创建订单等信息
            OrderCreateTo order = createOrder(addrId);
            // 2. 验价
            BigDecimal payAmount = order.getOrder().getPayAmount();
            BigDecimal voPayPrice = orderSubmitVo.getPayPrice();
            if (Math.abs(payAmount.subtract(voPayPrice).doubleValue()) < 0.01) {
                // 金额对比成功
                // 3.保存订单
                saveOrder(order);
                // 4.库存锁定
                WareSkuLockVo lockVo = new WareSkuLockVo();
                lockVo.setOrderSn(order.getOrder().getOrderSn());
                List<OrderItemVo> orderItemVos = order.getOrderItems().stream().map(item -> {
                    OrderItemVo itemVo = new OrderItemVo();
                    // 锁定的skuId 这个skuId要锁定的数量
                    itemVo.setSkuId(item.getSkuId());
                    itemVo.setCount(item.getSkuQuantity());
                    itemVo.setTitle(item.getSkuName());
                    return itemVo;
                }).collect(Collectors.toList());

                lockVo.setLocks(orderItemVos);
//                 远程锁库存
                R r = wareFeign.orderLockStock(lockVo);
                if (r.getCode() == 0) {
                    // 库存足够 锁定成功 给MQ发送消息
                    responseVo.setOrderEntity(order.getOrder());
                    rabbitTemplate.convertAndSend(orderEventExchange, createOrderRoutingKey, order.getOrder());
                } else {
                    // 锁定失败
                    responseVo.setCode(3);
                    Long skuId = r.getData(new TypeReference<Long>() {
                    });
                    throw new NoStockException(skuId);
                }
            } else {
                // 价格验证失败
                responseVo.setCode(2);
            }
        }
        return responseVo;
    }

    @Override
    public OrderEntity getOrderByOrderSn(String orderSn) {
        return this.getOne(new QueryWrapper<OrderEntity>().eq("order_sn", orderSn));
    }

    @Override
    public void closeOrder(OrderEntity entity) {
        // 查询这个订单的最新状态
        OrderEntity orderEntity = getById(entity.getId());
        if (OrderStatusEnum.CREATE_NEW.getCode().equals(orderEntity.getStatus())) {
            OrderEntity update = new OrderEntity();
            update.setId(entity.getId());
            update.setStatus(OrderStatusEnum.CANCLED.getCode());
            this.updateById(update);
            // 发送给MQ告诉它有一个订单被自动关闭了
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(orderEntity, orderTo);
            try {
                // 保证消息100%发出去 每一个消息在数据库保存详细信息
                // 定期扫描数据库 将失败的消息再发送一遍
                rabbitTemplate.convertAndSend(orderEventExchange, ReleaseOtherKey, orderTo);
            } catch (AmqpException e) {
                // 将没发送成功的消息进行重试发送.
            }
        }
    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        PayVo payVo = new PayVo();
        OrderEntity order = this.getOrderByOrderSn(orderSn);
        // 保留2位小数位向上补齐
        payVo.setTotal_amount(order.getTotalAmount().add(order.getFreightAmount() == null ? BigDecimal.ZERO : order.getFreightAmount()).setScale(2, BigDecimal.ROUND_UP).toString());
        payVo.setOut_trade_no(order.getOrderSn());
        List<OrderItemEntity> entities = orderItemService.list(
            new QueryWrapper<OrderItemEntity>()
                .eq("order_sn", order.getOrderSn()));
        payVo.setSubject("seven");
        payVo.setBody("seven");
        if (entities.get(0).getSkuName() != null && entities.get(0).getSkuName().length() > 1) {
			payVo.setSubject(entities.get(0).getSkuName());
			payVo.setBody(entities.get(0).getSkuName());
            payVo.setSubject("seven");
            payVo.setBody("seven");
        }
        return payVo;
    }

    @Override
    public PageUtils queryPageWithItem(Map<String, Object> params) {
        MemberRsepVo rsepVo = LoginUserInterceptor.THREAD_LOCAL.get();
        IPage<OrderEntity> page = this.page(
            new Query<OrderEntity>().getPage(params),
            // 查询这个用户的最新订单 [降序排序]
            new QueryWrapper<OrderEntity>().eq("member_id", rsepVo.getId()).orderByDesc("id")
        );
        List<OrderEntity> order_sn = page.getRecords().stream().map(order -> {
            // 查询这个订单关联的所有订单项
            List<OrderItemEntity> orderSn = orderItemService.list(new QueryWrapper<OrderItemEntity>().eq("order_sn", order.getOrderSn()));
            order.setItemEntities(orderSn);
            return order;
        }).collect(Collectors.toList());
        page.setRecords(order_sn);
        return new PageUtils(page);
    }

    @Override
    public String handlePayResult(PayAsyncVo vo) {

        // 1.保存交易流水
        PaymentInfoEntity infoEntity = new PaymentInfoEntity();
        infoEntity.setAlipayTradeNo(vo.getTrade_no());
        infoEntity.setOrderSn(vo.getOut_trade_no());
        //		TRADE_SUCCESS
        infoEntity.setPaymentStatus(vo.getTrade_status());
        infoEntity.setCallbackTime(vo.getNotify_time());
        infoEntity.setSubject(vo.getSubject());
        infoEntity.setTotalAmount(new BigDecimal(vo.getTotal_amount()));
        infoEntity.setCreateTime(vo.getGmt_create());
        paymentInfoService.save(infoEntity);

        // 2.修改订单状态信息
        if (vo.getTrade_status().equals("TRADE_SUCCESS") || vo.getTrade_status().equals("TRADE_FINISHED")) {
            // 支付成功
            String orderSn = vo.getOut_trade_no();
            this.baseMapper.updateOrderStatus(orderSn, OrderStatusEnum.PAYED.getCode());
        }
        return "success";
    }

    @Override
    public void createSecKillOrder(SecKillOrderTo secKillOrderTo) {
        logger.info("\n创建秒杀订单");
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(secKillOrderTo.getOrderSn());
        entity.setMemberId(secKillOrderTo.getMemberId());
        entity.setCreateTime(new Date());
        entity.setPayAmount(secKillOrderTo.getSeckillPrice());
        entity.setTotalAmount(secKillOrderTo.getSeckillPrice());
        entity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        entity.setPayType(1);
        // TODO 还有挺多的没设置
        BigDecimal price = secKillOrderTo.getSeckillPrice().multiply(new BigDecimal("" + secKillOrderTo.getNum()));
        entity.setPayAmount(price);

        this.save(entity);

        // 保存订单项信息
        OrderItemEntity itemEntity = new OrderItemEntity();
        itemEntity.setOrderSn(secKillOrderTo.getOrderSn());
        itemEntity.setRealAmount(price);
        itemEntity.setOrderId(entity.getId());
        itemEntity.setSkuQuantity(secKillOrderTo.getNum());
        R info = productFeign.getSpuInfoBySkuId(secKillOrderTo.getSkuId());
        SpuInfoVo spuInfo = info.getData(new TypeReference<SpuInfoVo>() {
        });
        itemEntity.setSpuId(spuInfo.getId());
        itemEntity.setSpuBrand(spuInfo.getBrandId().toString());
        itemEntity.setSpuName(spuInfo.getSpuName());
        itemEntity.setCategoryId(spuInfo.getCatalogId());
        itemEntity.setGiftGrowth(secKillOrderTo.getSeckillPrice().multiply(new BigDecimal(secKillOrderTo.getNum())).intValue());
        itemEntity.setGiftIntegration(secKillOrderTo.getSeckillPrice().multiply(new BigDecimal(secKillOrderTo.getNum())).intValue());
        itemEntity.setPromotionAmount(new BigDecimal("0.0"));
        itemEntity.setCouponAmount(new BigDecimal("0.0"));
        itemEntity.setIntegrationAmount(new BigDecimal("0.0"));
        orderItemService.save(itemEntity);
    }

    /**
     * 保存订单所有数据
     */
    private void saveOrder(OrderCreateTo order) {
        OrderEntity orderEntity = order.getOrder();
        orderEntity.setModifyTime(new Date());
        this.save(orderEntity);

        List<OrderItemEntity> orderItems = order.getOrderItems();
        orderItems = orderItems.stream().peek(item -> {
            item.setOrderId(orderEntity.getId());
            item.setSpuName(item.getSpuName());
            item.setOrderSn(order.getOrder().getOrderSn());
        }).collect(Collectors.toList());
        orderItemService.saveBatch(orderItems);
    }

    /**
     * 创建订单
     */
    private OrderCreateTo createOrder(Long addrId) {
        OrderCreateTo orderCreateTo = new OrderCreateTo();
        // 1. 生成订单号
        String orderSn = IdWorker.getTimeId();
        OrderEntity orderEntity = buildOrderSn(orderSn, addrId);
        // 2. 创建所有订单项
        List<OrderItemEntity> orderItemEntities = buildOrderItems(orderSn);
        // 3.验价 传入订单 、订单项 计算价格、积分、成长值等相关信息
        computerPrice(orderEntity, orderItemEntities);
        orderCreateTo.setOrder(orderEntity);
        orderCreateTo.setOrderItems(orderItemEntities);
        return orderCreateTo;
    }

    private void computerPrice(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntities) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        // 叠加每一个订单项的金额
        BigDecimal coupon = BigDecimal.ZERO;
        BigDecimal integration = BigDecimal.ZERO;
        BigDecimal promotion = BigDecimal.ZERO;
        BigDecimal gift = BigDecimal.ZERO;
        BigDecimal growth = BigDecimal.ZERO;
        for (OrderItemEntity item : orderItemEntities) {
            // 优惠券的金额
            coupon = coupon.add(item.getCouponAmount());
            // 积分优惠的金额
            integration = integration.add(item.getIntegrationAmount());
            // 打折的金额
            promotion = promotion.add(item.getPromotionAmount());
            BigDecimal realAmount = item.getRealAmount();
            totalPrice = totalPrice.add(realAmount);
            // 购物获取的积分、成长值
            gift = gift.add(BigDecimal.valueOf(item.getGiftIntegration()));
            growth = growth.add(BigDecimal.valueOf(item.getGiftGrowth()));
        }
        // 订单价格相关 总额、应付总额
        orderEntity.setTotalAmount(totalPrice);
        orderEntity.setPayAmount(totalPrice.add(orderEntity.getFreightAmount()));
        orderEntity.setPromotionAmount(promotion);
        orderEntity.setIntegrationAmount(integration);
        orderEntity.setCouponAmount(coupon);
        // 设置积分、成长值
        orderEntity.setIntegration(gift.intValue());
        orderEntity.setGrowth(growth.intValue());
        orderEntity.setAutoConfirmDay(14);
        // 设置订单的删除状态
        orderEntity.setDeleteStatus(OrderStatusEnum.CREATE_NEW.getCode());
    }

    /**
     * 为 orderSn 订单构建订单数据
     */
    private List<OrderItemEntity> buildOrderItems(String orderSn) {
        // 这里是最后一次来确认购物项的价格 这个远程方法还会查询一次数据库
        List<OrderItemVo> cartItems = productFeign.getCurrentUserCartItems();
        List<OrderItemEntity> itemEntities = null;
        if (cartItems != null && cartItems.size() > 0) {
            itemEntities = cartItems.stream().map(cartItem -> {
                OrderItemEntity itemEntity = buildOrderItem(cartItem);
                itemEntity.setOrderSn(orderSn);
                return itemEntity;
            }).collect(Collectors.toList());
        }
        return itemEntities;
    }

    /**
     * 构建订单项
     */
    private OrderItemEntity buildOrderItem(OrderItemVo cartItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        // 2.商品spu信息
        Long skuId = cartItem.getSkuId();
        R r = productFeign.getSpuInfoBySkuId(skuId);
        SpuInfoVo spuInfo = r.getData(new TypeReference<SpuInfoVo>() {
        });
        orderItemEntity.setSpuId(spuInfo.getId());
        orderItemEntity.setSpuBrand(spuInfo.getBrandId().toString());
        orderItemEntity.setSpuName(spuInfo.getSpuName());
        orderItemEntity.setCategoryId(spuInfo.getCatalogId());
        // 3.商品的sku信息
        orderItemEntity.setSkuId(cartItem.getSkuId());
        orderItemEntity.setSkuName(cartItem.getTitle());
        orderItemEntity.setSkuPic(cartItem.getImage());
        orderItemEntity.setSkuPrice(cartItem.getPrice());
        // 把一个集合按照指定的字符串进行分割得到一个字符串
        String skuAttr = StringUtils.collectionToDelimitedString(cartItem.getSkuAttr(), ";");
        orderItemEntity.setSkuAttrsVals(skuAttr);
        orderItemEntity.setSkuQuantity(cartItem.getCount());
        // 4.积分信息 买的数量越多积分越多 成长值越多
        orderItemEntity.setGiftGrowth(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getCount())).intValue());
        orderItemEntity.setGiftIntegration(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getCount())).intValue());
        // 5.订单项的价格信息 优惠金额
        orderItemEntity.setPromotionAmount(BigDecimal.ZERO);
        orderItemEntity.setCouponAmount(BigDecimal.ZERO);
        orderItemEntity.setIntegrationAmount(BigDecimal.ZERO);
        // 当前订单项的实际金额
        BigDecimal originPrice = orderItemEntity.getSkuPrice().multiply(BigDecimal.valueOf(orderItemEntity.getSkuQuantity()));
        // 减去各种优惠的价格
        BigDecimal realPrice = originPrice.subtract(orderItemEntity.getCouponAmount()).subtract(orderItemEntity.getPromotionAmount()).subtract(orderItemEntity.getIntegrationAmount());
        orderItemEntity.setRealAmount(realPrice);
        return orderItemEntity;
    }

    /**
     * 构建 OrderEntity
     */
    private OrderEntity buildOrderSn(String orderSn, Long addrId) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(orderSn);
        entity.setCreateTime(new Date());
        entity.setCommentTime(new Date());
        entity.setReceiveTime(new Date());
        entity.setDeliveryTime(new Date());
        MemberRsepVo memberRsepVo = LoginUserInterceptor.THREAD_LOCAL.get();
        entity.setMemberId(memberRsepVo.getId());
        entity.setMemberUsername(memberRsepVo.getUsername());
        entity.setBillReceiverEmail(memberRsepVo.getEmail());
        // 2. 获取收获地址信息
        R r = wareFeign.getFare(addrId);
        FareVo fareVo = r.getData(new TypeReference<FareVo>() {
        });
        entity.setFreightAmount(fareVo.getFare());
        entity.setReceiverCity(fareVo.getMemberAddressVo().getCity());
        entity.setReceiverDetailAddress(fareVo.getMemberAddressVo().getDetailAddress());
        entity.setDeleteStatus(OrderStatusEnum.CREATE_NEW.getCode());
        entity.setReceiverPhone(fareVo.getMemberAddressVo().getPhone());
        entity.setReceiverName(fareVo.getMemberAddressVo().getName());
        entity.setReceiverPostCode(fareVo.getMemberAddressVo().getPostCode());
        entity.setReceiverProvince(fareVo.getMemberAddressVo().getProvince());
        entity.setReceiverRegion(fareVo.getMemberAddressVo().getRegion());
        // 设置订单状态
        entity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        entity.setAutoConfirmDay(7);
        return entity;
    }

}