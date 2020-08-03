package com.czhe.sysmanage.taskJob;

import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/7/31 18:26
 * @description
 **/
@Component
public class MyJobAuto implements Job {

    private static Logger log = LoggerFactory.getLogger(MyJobAuto.class);

    public void befo() {
        log.info("******************MyJobAuto任务开始执行******************");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        befo();
        //定时任务处理的业务逻辑
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        log.info("******************MyJobAuto任务正在执行[{}]******************", name);
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JSONObject jsonObject = (JSONObject) jobDataMap.get("myValue");
        log.info("******************MyJobAuto任务[{}]携带的参数[{}]", name, jsonObject.toString());
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("******************当前时间[{}],MyJobAuto任务[{}]的线程名[{}]", time, name, Thread.currentThread().getName());
        after();

    }

    public void after() {
        log.info("******************MyJobAuto任务执行完毕******************");
    }
}