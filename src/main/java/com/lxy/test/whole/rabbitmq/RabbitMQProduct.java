package com.lxy.test.whole.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lxy on 2018/12/2.
 */
public class RabbitMQProduct {
    private final static String QUEUE_NAME = "hello";

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProduct.class);

    @Value("${rabbitmq.orderStatusExchangeName}")
    private String orderStatusExchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @KafkaListener(topics = "#{'${kafka.topics}'.split(',')}", containerFactory = "orderStatusMachineKafkaListenerContainerFactory")
    public void receive(Message<?> message) throws IOException {
        try {
            Map<String, Object> messageMap = new ObjectMapper().readValue(message.getPayload().toString(), Map.class);

            rabbitTemplate.setExchange(orderStatusExchangeName);
            rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString("massage"));
        } catch (Throwable e) {
            logger.error("invalid canal kafka message! message is {}, {}", message, ExceptionUtils.getStackTrace(e));
        }
    }
}
