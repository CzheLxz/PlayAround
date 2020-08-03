package com.czhe.sysmanage.listener;

import com.alibaba.fastjson.JSONObject;
import com.czhe.sysmanage.config.JobEntity;
import com.czhe.sysmanage.service.QuartzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.UUID;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/8/3 9:41
 * @description
 **/
@Configuration
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = LoggerFactory.getLogger(ApplicationStartListener.class);

    @Autowired
    private QuartzService quartzService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(UUID.randomUUID().toString().replaceAll("-", ""));
        jobEntity.setClassName("com.czhe.sysmanage.taskJob.MyJobAuto");
        jobEntity.setCronExpression("0 0/5 * * * ?");//设置为五分钟执行一次
        jobEntity.setJobName("AutoJob");
        jobEntity.setJobGroup("AutoJobGroup");
        jobEntity.setTriggerName("AutoJobTrigger");
        jobEntity.setTrigerGroup("AutoJobTriggerGroup");
        jobEntity.setDescription("跟随项目启动的定时Job");
        //可以将任务跟数据库挂钩,做个任务管理模块,获取需要自启动的任务,记录各个参数等
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "TestValue");
        jobEntity.setData(jsonObject);
        quartzService.addJob(jobEntity);
        log.info("--------触发定时任务开始执行........");
        log.info("--------application启动完毕--------");

    }
}