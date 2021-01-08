package com.czhe.sysmanage.thread;

import com.czhe.sysmanage.session.SseSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/1/8 14:49
 * @description 心跳任务
 **/
public class HeartBeatTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatTask.class);

    private final String clientId;

    public HeartBeatTask(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {
        logger.info("MSG: SseHeartbeat | ID: {} | Date: {}", clientId, new Date());
        SseSession.send(clientId, "ping");
    }
}