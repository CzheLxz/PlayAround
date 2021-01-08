package com.czhe.sysmanage.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/1/8 14:26
 * @description
 **/
public interface SseService {

    SseEmitter start(String clientId);

    String send(String clientId, Object msg);

    String close(String clientId);


}