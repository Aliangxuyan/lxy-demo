package com.lxy.test.middle.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author lxy
 * @date 2019/6/28
 */
public class JmsConsumer {
    public static final String ACTIVE_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        //1、创建工厂连接对象，需要制定ip和端口号
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象,第一个参数事务，第二个参数签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue(QUEUE_NAME);
        //6、使用会话对象创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        // ***********************************第一种消费，直接消费************************************************
//        while (true) {
//            //8、发送消息,不设置参数一直有消费者，一直等候
////            TextMessage textMessage = (TextMessage) consumer.receive();
//            TextMessage textMessage = (TextMessage) consumer.receive(4000l);
//            if (null != textMessage) {
//                System.out.println("********消费者收到消息：" + textMessage.getText());
//            } else {
//                break;
//            }
//        }
        // ***********************************第二种消费，监听消费*********************************************
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                if (null != message && message instanceof TextMessage) {
                    try {
                        System.out.println("********消费者收到消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
