package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.service.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/1/8 15:05
 * @description 服务器发送事件
 **/

@RequestMapping("/sse")
@RestController
public class ServerSentEventsController {

    @Autowired
    private SseService sseService;

    @RequestMapping("/start")
    public SseEmitter start(@RequestParam String clientId) {
        return sseService.start(clientId);
    }


    @RequestMapping("/end")
    public String close(@RequestParam String clientId) {
        return sseService.close(clientId);
    }

    @RequestMapping("/send")
    public String send(@RequestParam String clientId) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("code", "200");
        msg.put("data", "success");
        return sseService.send(clientId, msg);
    }
}