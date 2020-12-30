package com.czhe.sysmanage.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/16 17:00
 * @description
 **/
@Configuration
public class MyRabbitConfig {

    @Autowired
    //private ChatService chatService;

    //绑定键
    private final static String msgTopicKey = "topic.public";
    //队列
    private final static String msgTopocQueue = "topicQueue";

    @Bean
    public Queue topicQueue() {
        return new Queue(msgTopocQueue, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicWebSocketExchange", true, false);
    }

    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(topicQueue()).to(exchange()).with(msgTopicKey);
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1",5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return  connectionFactory;
    }
}