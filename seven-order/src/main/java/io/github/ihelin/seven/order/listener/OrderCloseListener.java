package io.github.ihelin.seven.order.listener;

import com.rabbitmq.client.Channel;
import io.github.ihelin.seven.order.entity.OrderEntity;
import io.github.ihelin.seven.order.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RabbitListener(queues = "${myRabbitmq.MQConfig.queues}")
public class OrderCloseListener {

	@Autowired
	private OrderService orderService;

	@RabbitHandler
	public void listener(OrderEntity entity, Channel channel, Message message) throws IOException {
		try {
			orderService.closeOrder(entity);
			// 手动调用支付宝收单
			channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
		} catch (Exception e) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
		}
	}
}
