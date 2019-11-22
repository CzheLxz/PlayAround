package com.demo.web.listener;

import com.demo.service.MessageService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 14:27
 * @description
 **/
@Component
public class MessageServiceContext {

    private final Map<Integer, MessageService> handlerMap = new HashMap<>();

    public MessageService getMessageService(Integer code) {
        return handlerMap.get(code);
    }

    public void putMessageService(Integer code, MessageService messageService) {
        handlerMap.put(code, messageService);
    }

    public void showHandlerMap() {
        handlerMap.forEach((code, service) -> {
            System.out.println(code + ":" + service);
        });
    }

}
