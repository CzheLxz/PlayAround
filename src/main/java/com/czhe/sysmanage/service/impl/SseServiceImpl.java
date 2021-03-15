package com.czhe.sysmanage.service.impl;

import com.czhe.sysmanage.retrunHandle.BizException;
import com.czhe.sysmanage.service.SseService;
import com.czhe.sysmanage.session.SseSession;
import com.czhe.sysmanage.thread.HeartBeatTask;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/1/8 14:31
 * @description
 **/
@Service
public class SseServiceImpl implements SseService {

    private static final Logger log = LoggerFactory.getLogger(SseServiceImpl.class);

    /**
     * 发送心跳线程池
     */
    private static ScheduledExecutorService heartbeatExecutors = new ScheduledThreadPoolExecutor(8,
            new BasicThreadFactory.
                    Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());


    /**
     * 新建连接
     *
     * @param clientId
     * @return
     */
    @Override
    public SseEmitter start(String clientId) {
        //设置30秒超时
        SseEmitter emitter = new SseEmitter(30_000L);
        log.info("Msg: SseConnect| EmitterHash: {} | ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
        SseSession.add(clientId, emitter);
        final ScheduledFuture<?> future = heartbeatExecutors.scheduleAtFixedRate(new HeartBeatTask(clientId), 0, 10, TimeUnit.SECONDS);
        emitter.onCompletion(() -> {
            log.info("MSG: SseConnectCompletion | EmitterHash: {} |ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
            SseSession.onCompletion(clientId, future);
        });
        emitter.onTimeout(() -> {
            log.error("MSG: SseConnectTimeout | EmitterHash: {} |ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
            SseSession.onError(clientId, new BizException("TimeOut(clientId: " + clientId + ")"));
        });
        emitter.onError(t -> {
            log.error("MSG: SseConnectError | EmitterHash: {} |ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
            SseSession.onError(clientId, new BizException("Error(clientId: " + clientId + ")"));
        });
        return emitter;
    }

    @Override
    public String send(String clientId, Object msg) {
        if (SseSession.send(clientId, msg)) {
            return "succeed";
        }
        return "error";
    }

    @Override
    public String close(String clientId) {
        log.info("MSG: SseConnectClose | ID: {} | Date: {}", clientId, new Date());
        if (SseSession.del(clientId)) {
            return "succeed";
        }
        return "error";
    }
}