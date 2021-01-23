package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.dto.mq.SecKillOrderTo;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderEntity;
import io.github.ihelin.seven.order.vo.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * oms_order
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 给订单确认页返回需要的数据
     */
    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    /**
     * 下单操作
     */
    SubmitOrderResponseVo submitOrder(OrderSubmitVo submitVo);

    OrderEntity getOrderByOrderSn(String orderSn);

    void closeOrder(OrderEntity entity);

    /**
     * 获取当前订单的支付信息
     */
    PayVo getOrderPay(String orderSn);

    PageUtils queryPageWithItem(Map<String, Object> params);

    /**
     * 处理支付宝的返回数据
     */
    String handlePayResult(PayAsyncVo vo);

    void createSecKillOrder(SecKillOrderTo secKillOrderTo);
}

