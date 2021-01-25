package io.github.ihelin.seven.ware.listener;

import com.rabbitmq.client.Channel;
import io.github.ihelin.seven.common.dto.mq.OrderTo;
import io.github.ihelin.seven.common.dto.mq.StockLockedTo;
import io.github.ihelin.seven.ware.service.WareSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author iHelin
 * @since 2021/1/25 19:59
 */
@Component
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WareSkuService wareSkuService;

    @RabbitHandler
    public void handleStockLockedRelease(StockLockedTo stockLockedTo, Message message, Channel channel) throws IOException {
        logger.debug("收到解锁库存的消息");
        try {
            wareSkuService.unlockStock(stockLockedTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @RabbitHandler
    public void handleOrderCloseRelease(OrderTo orderTo, Message message, Channel channel) throws IOException {
        logger.debug("收到订单关闭消息，准备解锁库存");
        try {
            wareSkuService.unlockStock(orderTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

}
