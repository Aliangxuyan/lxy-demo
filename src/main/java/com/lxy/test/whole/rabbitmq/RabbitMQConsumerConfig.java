package com.lxy.test.whole.rabbitmq;

import com.lxy.test.whole.rabbitmq.OrderStatusTopicTest;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by lxy on 2018/7/1.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConsumerConfig {
    private static final String LISTENER_METHOD = "invoke";

    private String orderStatusTopicExchangeName;
    private String orderStatusTestTopicQueueName;
    private String orderStatusTestTopicQueueNoConsumeName;
    private String routeKey;
    private String routeKeyNoConsume;


    // 正常流程测试
    @Bean
    Queue orderStatusTestTopicQueue(){
        return new Queue(orderStatusTestTopicQueueName);
    }

    @Bean
    TopicExchange orderStatusTestTopicExchange(){
        return new TopicExchange(orderStatusTopicExchangeName);
    }

    @Bean
    Binding orderStatusTopicTestBinding(@Qualifier("orderStatusTestTopicQueue") Queue queue, @Qualifier("orderStatusTestTopicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routeKey);
    }

    @Bean
    MessageListenerAdapter orderStatusTopicTestMessageListenerAdapter(OrderStatusTopicTest orderStatusTopicTest){
        return new MessageListenerAdapter(orderStatusTopicTest,LISTENER_METHOD);
    }

    @Bean
    SimpleMessageListenerContainer orderStatusTopicTestListenerContainer(ConnectionFactory connectionFactory,
                                                                         @Qualifier("orderStatusTopicTestMessageListenerAdapter") MessageListenerAdapter  messageListenerAdapter){
        return getSimpleMessageListenerContainer(connectionFactory,messageListenerAdapter,orderStatusTestTopicQueueName);
    }


    // queue 消息积压测试
    @Bean
    Queue orderStatusTestTopicQueueNoConsume(){
        return new Queue(orderStatusTestTopicQueueNoConsumeName);
    }

    @Bean
    TopicExchange orderStatusTestTopicExchangeNoConsume(){
        return new TopicExchange(orderStatusTopicExchangeName);
    }

    @Bean
    Binding orderStatusTopicTestBindingNoConsume(@Qualifier("orderStatusTestTopicQueueNoConsume") Queue queue, @Qualifier("orderStatusTestTopicExchangeNoConsume") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routeKeyNoConsume);
    }

    private SimpleMessageListenerContainer getSimpleMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                             MessageListenerAdapter listenerAdapter,
                                                                             String queueName) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        container.setErrorHandler(new ConditionalRejectingErrorHandler(
            t -> t instanceof ListenerExecutionFailedException));
        container.setConcurrentConsumers(10);
        return container;
    }


}
