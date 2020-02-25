package com.lxy.test.middle.whole.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Created by lxy on 2018/7/1.
 */
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConfig {
}
