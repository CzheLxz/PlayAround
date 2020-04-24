package com.czhe.sysmanage;

import com.czhe.sysmanage.entity.MSG_TYPE;
import com.czhe.sysmanage.entity.MessageInfo;
import com.czhe.sysmanage.entity.User;
import com.czhe.sysmanage.listener.MessageServiceContext;
import com.czhe.sysmanage.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
class SysmanageApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(SysmanageApplicationTests.class);

    @Autowired
    MessageServiceContext messageServiceContext;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void contextLoads() {
        messageServiceContext.showHandlerMap();
        MessageInfo messageInfo = new MessageInfo(MSG_TYPE.TEXT.code, "消息内容: 将近酒 杯莫停");
        MessageService messageService = messageServiceContext.getMessageService(messageInfo.getCode());
        messageService.handleMessage(messageInfo);
    }

    @Test
    public void get() {
        //测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 100).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("字符串缓存结果 - {}", k1);
        String key = "u1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "czhe", "123456", "admin", false));
        final User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.info("对象缓存结果 - {}", user);
    }

    @Test
    public void getBean() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(bean -> {
            System.out.println(bean + " Type:  " + applicationContext.getBean(bean).getClass());
        });
    }


}





