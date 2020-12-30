package com.czhe.sysmanage.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/11 15:18
 * @description websocket configuration
 **/
//@Configuration
//@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private GetHeaderParamInterceptor getHeaderParamInterceptor;

    @Override
    public void registerStompEndpoints (StompEndpointRegistry registry){
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        //暴露多个节点就一样直接addEndpoint就可以
        /*registry.addEndpoint("/alone") .setAllowedOrigins("*")
              .withSockJS();*/
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 使用RabbitMQ做为消息代理 替换默认的Simple Broker
        //定义了服务端接收地址的前缀 也即客户端给服务端发消息的地址前缀 @SendTo(XXX) 也可以重定向
        registry.setUserDestinationPrefix("/user");//这是给sendToUser使用 前端订阅需要加上/user
        registry.setApplicationDestinationPrefixes("/app");//这是给客户端推送消息到服务器使用 推送的接口加上/app
        // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
        registry.enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue")
                .setVirtualHost("/")
                .setRelayHost("localhost")
                .setClientLogin("admin")
                .setClientPasscode("123456")
                .setSystemLogin("admin")
                .setSystemPasscode("123456")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);

    }

    /**
     * 采用自定义拦截器 获取connect时候传递的参数
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        registration.interceptors(getHeaderParamInterceptor);
    }
}