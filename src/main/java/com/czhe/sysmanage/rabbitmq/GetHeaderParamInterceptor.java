package com.czhe.sysmanage.rabbitmq;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/14 10:28
 * @description
 **/
@Component
public class GetHeaderParamInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                Object name = ((Map) raw).get("username"); //取出客户端携带的参数
                System.out.println(name);
                if (name instanceof LinkedList) {
                    // 设置当前访问的认证用户
                    accessor.setUser(new UserPrincipal(((LinkedList) name).get(0).toString()));
                }
            }
        }
        return message;
    }

}