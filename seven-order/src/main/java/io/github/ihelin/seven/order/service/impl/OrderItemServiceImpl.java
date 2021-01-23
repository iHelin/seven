package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.OrderItemDao;
import io.github.ihelin.seven.order.entity.OrderItemEntity;
import io.github.ihelin.seven.order.entity.OrderReturnReasonEntity;
import io.github.ihelin.seven.order.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * oms_order_item
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
            new Query<OrderItemEntity>().getPage(params),
            new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @param message org.springframework.amqp.core.Message
     */
    @RabbitListener(queues = {"hello-seven-queue"})
    public void receiveMsg(Message message, OrderReturnReasonEntity entity, Channel channel) {
        logger.debug("msg:{}", message);
        logger.debug("entity:{}", entity);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            logger.error("签收失败", e);
        }
    }

}