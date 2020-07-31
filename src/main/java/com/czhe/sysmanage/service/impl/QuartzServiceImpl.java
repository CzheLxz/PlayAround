package com.czhe.sysmanage.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.czhe.sysmanage.config.JobEntity;
import com.czhe.sysmanage.service.QuartzService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/7/31 17:15
 * @description
 **/
@Service
public class QuartzServiceImpl implements QuartzService {
    private static Logger log = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Override
    public Boolean addJob(JobEntity job) {
        try {
            JSONObject data = job.getData();
            log.info("当前任务携带的业务参数={}", data.toJSONString());
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("myValue", data);
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            JobDetail jobDetail = JobBuilder
                    .newJob((Class<? extends Job>) Class.forName(job.getClassName()))
                    //制定执行类
                    .withIdentity(jobUnique, job.getJobGroup())
                    //指定name和group
                    .requestRecovery().withDescription(job.getDescription())
                    .setJobData(jobDataMap)
                    .build();
            //创建表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            //创建触发器
            CronTrigger cronTrigger = TriggerBuilder
                    .newTrigger().withIdentity(job.getTriggerName(), job.getTrigerGroup())
                    .withDescription(job.getDescription())
                    .withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
            log.info("定时任务[{}]创建成功,开始执行", jobId + jobName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean runJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.triggerJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]执行成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTrigerGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            //重新构建表达式
            CronTrigger trigger = cronTrigger.getTriggerBuilder()
                    .withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
                    .withDescription(job.getDescription())
                    .build();
            scheduler.rescheduleJob(triggerKey, trigger);
            log.info("定时任务[{}]更新成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean pauseJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.pauseJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]暂停成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean resumeJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.resumeJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]唤醒成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.deleteJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]删除成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public JSONObject queryJob(JobEntity job) {
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTrigerGroup());
        try {
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == cronTrigger) {
                return null;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("expression", cronTrigger.getCronExpression());
            jsonObject.put("state", scheduler.getTriggerState(triggerKey));
            jsonObject.put("description", cronTrigger.getDescription());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}