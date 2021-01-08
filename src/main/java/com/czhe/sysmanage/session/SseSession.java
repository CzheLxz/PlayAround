package com.czhe.sysmanage.session;

import com.czhe.sysmanage.retrunHandle.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/1/8 10:54
 * @description
 **/
public class SseSession {

    private static final Logger log = LoggerFactory.getLogger(SseSession.class);

    private static Map<String, SseEmitter> session = new ConcurrentHashMap<>();

    public static boolean exist(String id) {
        return session.get(id) == null;
    }

    public static void add(String id, SseEmitter sseEmitter) {
        final SseEmitter oldEmitter = session.get(id);
        if (oldEmitter != null) {
            oldEmitter.completeWithError(new BizException("RepeatConnect(Id:" + id + ")"));
        }
        session.put(id, sseEmitter);
    }

    public static boolean del(String id) {
        final SseEmitter emitter = session.remove(id);
        if (emitter != null) {
            emitter.complete();
            return true;
        }
        return false;
    }

    public static boolean send(String id, Object msg) {
        final SseEmitter emitter = session.get(id);
        if (emitter != null) {
            try {
                emitter.send(msg);
                return true;
            } catch (IOException e) {
                log.error("Msg: SendMessageError-IOException | ID: " + id, "Date: " + new Date(), e);
                return false;
            }
        }
        return false;
    }


    public static void onCompletion(String id, ScheduledFuture<?> future) {
        session.remove(id);
        if (future != null) {
            //SseEmitter断开中断心跳发送
            future.cancel(true);
        }
    }

    /**
     * onTimeOut OnError 后执行
     *
     * @param id
     * @param e
     */
    public static void onError(String id, BizException e) {
        final SseEmitter emitter = session.get(id);
        if (emitter != null) {
            emitter.completeWithError(e);
        }
    }
}