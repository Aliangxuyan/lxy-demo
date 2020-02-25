package com.lxy.test.middle.whole.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by lxy on 2018/7/1.
 */
public class ProducerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

}
