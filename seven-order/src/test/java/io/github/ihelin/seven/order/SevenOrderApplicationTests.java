package io.github.ihelin.seven.order;

import io.github.ihelin.seven.order.entity.OrderReturnReasonEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SevenOrderApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
     */
    @Test
    void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello-seven");
        amqpAdmin.declareExchange(directExchange);
        logger.debug("DirectExchange 创建成功。");
    }

    /**
     * Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments)
     */
    @Test
    void createQueue() {
        Queue queue = new Queue("hello-seven-queue");
        amqpAdmin.declareQueue(queue);
        logger.debug("Queue 创建成功。");
    }

    /**
     * Binding(String destination, DestinationType destinationType, String exchange, String routingKey,
     *
     * @Nullable Map<String, Object> arguments)
     */
    @Test
    void createBinding() {
        Binding binding = new Binding("hello-seven-queue", Binding.DestinationType.QUEUE, "hello-seven", "hello.seven", null);
        amqpAdmin.declareBinding(binding);
        logger.debug("Binding 创建成功。");
    }

    @Test
    void sendMessage() {
        OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
        reasonEntity.setId(1L);
        reasonEntity.setCreateTime(new Date());
        reasonEntity.setName("123456");
        reasonEntity.setSort(1);
        reasonEntity.setStatus(0);
        rabbitTemplate.convertAndSend("hello-seven", "hello.seven", reasonEntity);
        logger.debug("消息发送成功。");
    }

}
