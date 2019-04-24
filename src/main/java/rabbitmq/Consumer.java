package rabbitmq;

import config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by lxy on 2018/7/1.
 */
@Component
public class Consumer {
    Logger logger = LoggerFactory.getLogger(Consumer.class);
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        logger.info("consume:{}",message);
    }
}
