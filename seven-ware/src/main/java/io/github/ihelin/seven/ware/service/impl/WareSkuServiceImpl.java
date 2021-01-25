package io.github.ihelin.seven.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.ihelin.seven.common.dto.SkuHasStockVo;
import io.github.ihelin.seven.common.dto.mq.OrderTo;
import io.github.ihelin.seven.common.dto.mq.StockDetailTo;
import io.github.ihelin.seven.common.dto.mq.StockLockedTo;
import io.github.ihelin.seven.common.enums.OrderStatusEnum;
import io.github.ihelin.seven.common.exception.NoStockException;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.ware.dao.WareSkuDao;
import io.github.ihelin.seven.ware.entity.WareOrderTaskDetailEntity;
import io.github.ihelin.seven.ware.entity.WareOrderTaskEntity;
import io.github.ihelin.seven.ware.entity.WareSkuEntity;
import io.github.ihelin.seven.ware.feign.OrderFeign;
import io.github.ihelin.seven.ware.feign.ProductFeign;
import io.github.ihelin.seven.ware.service.WareOrderTaskDetailService;
import io.github.ihelin.seven.ware.service.WareOrderTaskService;
import io.github.ihelin.seven.ware.service.WareSkuService;
import io.github.ihelin.seven.ware.vo.OrderItemVo;
import io.github.ihelin.seven.ware.vo.WareSkuLockVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private WareOrderTaskService wareOrderTaskService;
    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderFeign orderFeign;

    @Value("${myRabbitmq.MQConfig.stockEventExchange}")
    private String stockEventExchange;

    @Value("${myRabbitmq.MQConfig.lockStockRoutingKey}")
    private String lockStockRoutingKey;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (StringUtils.isNotEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }

        IPage<WareSkuEntity> page = this.page(new Query<WareSkuEntity>().getPage(params), queryWrapper);

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        List<WareSkuEntity> entities = baseMapper.selectList(new QueryWrapper<WareSkuEntity>()
            .eq("sku_id", skuId).eq("ware_id", wareId));
        if (entities.size() > 0) {
            baseMapper.addStock(skuId, wareId, skuNum);
        } else {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            R r = productFeign.info(skuId);
            Map<String, Object> data = (Map<String, Object>) r.get("data");
            wareSkuEntity.setSkuName((String) data.get("skuName"));
            baseMapper.insert(wareSkuEntity);
        }
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        List<SkuHasStockVo> skuHasStockVos = skuIds.stream().map(skuId -> {
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            Long count = baseMapper.getSkuStock(skuId);
            skuHasStockVo.setSkuId(skuId);
            skuHasStockVo.setHasStock(count != null && count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return skuHasStockVos;
    }

    /**
     * 库存解锁的场景
     * 1.下订单成功，订单过期没有支付被系统或用户取消
     * 2.下单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚，但库存没有回滚，之前锁定的库存就需要自动解锁
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = NoStockException.class)
    public Boolean orderLockStock(WareSkuLockVo vo) {
        // 当定库存之前先保存订单 以便后来消息撤回
        WareOrderTaskEntity taskEntity = new WareOrderTaskEntity();
        taskEntity.setOrderSn(vo.getOrderSn());
        wareOrderTaskService.save(taskEntity);
        // [理论上]1. 按照下单的收获地址 找到一个就近仓库, 锁定库存
        // [实际上]1. 找到每一个商品在那个一个仓库有库存
        List<OrderItemVo> locks = vo.getLocks();
        List<SkuWareHasStock> skuWareHasStocks = locks.stream().map(item -> {
            SkuWareHasStock skuWareHasStock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            skuWareHasStock.setSkuId(skuId);
            // 查询这两个商品在哪有库存
            List<Long> wareIds = baseMapper.listWareIdHasSkuStock(skuId);
            skuWareHasStock.setWareId(wareIds);
            skuWareHasStock.setNum(item.getCount());
            return skuWareHasStock;
        }).collect(Collectors.toList());

        for (SkuWareHasStock hasStock : skuWareHasStocks) {
            boolean skuLocked = false;
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareId();
            if (wareIds == null || wareIds.size() == 0) {
                // 没有任何仓库有这个库存
                throw new NoStockException(skuId);
            }
            // 如果每一个商品都锁定成功 将当前商品锁定了几件的工作单记录发送给MQ
            // 如果锁定失败 前面保存的工作单信息回滚了
            for (Long wareId : wareIds) {
                // 成功就返回 1 失败返回0
                Long count = baseMapper.lockSkuStock(skuId, wareId, hasStock.getNum());
                if (count == 1) {
                    skuLocked = true;
                    // 告诉MQ库存锁定成功 一个订单锁定成功 消息队列就会有一个消息
                    WareOrderTaskDetailEntity detailEntity = new WareOrderTaskDetailEntity(null, skuId,
                        "", hasStock.getNum(), taskEntity.getId(), wareId, 1);
                    wareOrderTaskDetailService.save(detailEntity);
                    StockLockedTo stockLockedTo = new StockLockedTo();
                    stockLockedTo.setId(taskEntity.getId());
                    StockDetailTo stockDetailTo = new StockDetailTo();
                    BeanUtils.copyProperties(detailEntity, stockDetailTo);
                    // 防止回滚以后找不到数据 把详细信息页
                    stockLockedTo.setDetailTo(stockDetailTo);
                    rabbitTemplate.convertAndSend(stockEventExchange, lockStockRoutingKey, stockLockedTo);
                    break;
                }
                // 当前仓库锁定失败 重试下一个仓库
            }
            if (!skuLocked) {
                // 当前商品在所有仓库都没锁柱
                throw new NoStockException(skuId);
            }
        }
        // 3.全部锁定成功
        return true;
    }

    /**
     * 防止订单服务卡顿 导致订单状态一直改不了 库存消息有限到期 最后导致卡顿的订单 永远无法解锁库存
     */
    @Transactional
    @Override
    public void unlockStock(OrderTo to) {
        String orderSn = to.getOrderSn();
        // 查一下最新的库存状态 防止重复解锁库存[Order服务可能会提前解锁]
        WareOrderTaskEntity taskEntity = wareOrderTaskService.getOrderTaskByOrderSn(orderSn);
        Long taskEntityId = taskEntity.getId();
        // 按照工作单找到所有 没有解锁的库存 进行解锁 状态为1等于已锁定
        List<WareOrderTaskDetailEntity> orderTaskDetailEntities = wareOrderTaskDetailService
            .list(new QueryWrapper<WareOrderTaskDetailEntity>()
                .eq("task_id", taskEntityId)
                .eq("lock_status", 1));
        for (WareOrderTaskDetailEntity entity : orderTaskDetailEntities) {
            unLockStock(entity.getSkuId(), entity.getWareId(), entity.getSkuNum(), entity.getId());
        }
    }

    @Override
//    @Transactional
    public void unlockStock(StockLockedTo to) {
        // 库存id
        Long id = to.getId();
        StockDetailTo detailTo = to.getDetailTo();
        Long detailId = detailTo.getId();
        /*
         * 解锁库存
         * 	查询数据库关系这个订单的详情
         * 		有: 证明库存锁定成功
         * 			1.没有这个订单, 必须解锁
         * 			2.有这个订单 不是解锁库存
         * 				订单状态：已取消,解锁库存
         * 				没取消：不能解锁，扣库存;
         * 		没有：就是库存锁定失败，库存回滚了 这种情况无需回滚
         */
        WareOrderTaskDetailEntity wareOrderTaskDetailEntity = wareOrderTaskDetailService.getById(detailId);
        if (wareOrderTaskDetailEntity != null) {
            // 解锁
            WareOrderTaskEntity taskEntity = wareOrderTaskService.getById(id);
            String orderSn = taskEntity.getOrderSn();
            // 根据订单号 查询订单状态 已取消才解锁库存
            R r = orderFeign.getOrderStatus(orderSn);
            if (r.getCode() == 0) {
                // 订单数据返回成功
                Integer orderStatus = r.getData(new TypeReference<Integer>() {
                });
                // 订单不存在
                if (orderStatus == null || OrderStatusEnum.CANCLED.getCode().equals(orderStatus)) {
                    // 订单已取消 状态1:已锁定才可以解锁
                    if (wareOrderTaskDetailEntity.getLockStatus() == 1) {
                        unLockStock(detailTo.getSkuId(), detailTo.getWareId(), detailTo.getSkuNum(), detailId);
                    }
                }
            } else {
                // 消息拒绝 重新放回队列 让别人继续消费解锁
                throw new RuntimeException("远程服务失败");
            }
        } else {
            // 无需解锁
        }
    }

    /**
     * 解锁库存
     */
    private void unLockStock(Long skuId, Long wareId, Integer num, Long taskDetailId) {
        // 更新库存
        baseMapper.unlockStock(skuId, wareId, num);
        // 更新库存工作单的状态
        WareOrderTaskDetailEntity detailEntity = new WareOrderTaskDetailEntity();
        detailEntity.setId(taskDetailId);
        detailEntity.setLockStatus(2);
        wareOrderTaskDetailService.updateById(detailEntity);
    }

    static class SkuWareHasStock {
        private Long skuId;
        private List<Long> wareId;
        private Integer num;

        public Long getSkuId() {
            return skuId;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public List<Long> getWareId() {
            return wareId;
        }

        public void setWareId(List<Long> wareId) {
            this.wareId = wareId;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }

}