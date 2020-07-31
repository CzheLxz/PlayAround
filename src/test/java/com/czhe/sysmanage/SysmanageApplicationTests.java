package com.czhe.sysmanage;

import com.alibaba.fastjson.JSONObject;
import com.czhe.sysmanage.entity.MSG_TYPE;
import com.czhe.sysmanage.entity.MessageInfo;
import com.czhe.sysmanage.entity.Person;
import com.czhe.sysmanage.entity.User;
import com.czhe.sysmanage.listener.MessageServiceContext;
import com.czhe.sysmanage.service.MessageService;
import com.czhe.sysmanage.service.PersonService;
import com.jayway.jsonpath.JsonPath;
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
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
class SysManageApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(SysManageApplicationTests.class);

    @Autowired
    MessageServiceContext messageServiceContext;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    PersonService personService;


    @Test
    public void contextLoads() {
        messageServiceContext.showHandlerMap();
        MessageInfo messageInfo = new MessageInfo(MSG_TYPE.TEXT.code, "消息内容: 将近酒 杯莫停");
        MessageService messageService = messageServiceContext.getMessageService(messageInfo.getCode());
        messageService.handleMessage(messageInfo);
    }

    @Test
    public void redis() {
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

    @Test
    public void insertRecords() {
        RunThread runThread = new RunThread();
        runThread.run();
    }

    @Test
    public void testCache() {
        Person person = new Person("9996550670824321b9b8cde2c3e3ee74", "keven", "Product", "male", 32, 20000);
        personService.updateByPrimaryKeySelective(person);
        final Person ps = personService.selectByPrimaryKey("9996550670824321b9b8cde2c3e3ee74");
        log.info("cache person >>>>>>>>>>>>>> " + ps.toString());
    }

    class RunThread implements Runnable {

        @Override
        public void run() {
            Person person;
            Random rand;
            String[] job = {"Development", "Personnel", "Operation", "Product", "examination"};
            String[] gender = {"male", "female", "unknown"};
            int a = 0, b = 0;
            for (int i = 0; i <= 10000; i++) {
                if (a > 4) {
                    a = 0;
                }
                if (b > 2) {
                    b = 0;
                }
                rand = new Random();
                person = new Person(UUID.randomUUID().toString().replaceAll("-", ""), createName(4, 6), job[a], gender[b], rand.nextInt(13) + 22, (rand.nextInt(171) + 50) * 100);
                System.out.println(person.toString());
                personService.insert(person);
                a++;
                b++;
            }
        }

    }

    public static String createName(int min, int max) {
        int count = (int) (Math.random() * (max - min + 1)) + min;
        String str = "";
        for (int i = 0; i < count; i++) {
            str += (char) ((int) (Math.random() * 26) + 'a');
        }
        return str;
    }

    @Test
    public void getJsonValue(){
        JSONObject json = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject person = new JSONObject();
        JSONObject manager = new JSONObject();
        person.put("name","zjc");
        person.put("age","22");
        person.put("height",160);
        manager.put("name","czhe");
        manager.put("age","23");
        manager.put("height",180);
        data.put("person",person);
        data.put("manager",manager);
        result.put("code","100");
        result.put("data",data);
        json.put("result",result);
        System.out.println(json.toJSONString());
        List<String> cityName = JsonPath.read(json, "$..name");//获取所有name节点
        Object czhe = JsonPath.read(json, "$..[?(@.name == 'czhe')]");//根据name获取值
        Object zjc = JsonPath.read(json, "$..[?(@.height < 170)]");//根据height获取值
        cityName.stream().forEach(name ->{
            System.out.println(name);
        });
        System.out.println(czhe.toString());
        System.out.println(zjc.toString());

    }


}






