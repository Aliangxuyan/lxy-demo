package com.lxy.test.middle.whole.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by lxy on 2018/12/5.
 */
@Component("orderStatusTopicTest")
public class OrderStatusTopicTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatusTopicTest.class);
    public void invoke(String message){
        logger.info("测试rabbitmq 消息消费:{}",message);
    }
}
