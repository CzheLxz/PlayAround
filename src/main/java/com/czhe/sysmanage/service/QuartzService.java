package com.czhe.sysmanage.service;

import com.alibaba.fastjson.JSONObject;
import com.czhe.sysmanage.config.JobEntity;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/7/31 17:12
 * @description
 **/
public interface QuartzService {

    /**
     * 创建Job
     *
     * @param job
     * @return
     */
    Boolean addJob(JobEntity job);

    /**
     * 执行Job
     *
     * @param job
     * @return
     */
    Boolean runJob(JobEntity job);

    /**
     * 修改Job
     *
     * @param job
     */
    Boolean updateJob(JobEntity job);

    /**
     * 暂定Job
     *
     * @param job
     */
    Boolean pauseJob(JobEntity job);

    /**
     * 唤醒Job
     *
     * @param job
     */
    Boolean resumeJob(JobEntity job);

    /**
     * 删除Job
     *
     * @param job
     */
    Boolean deleteJob(JobEntity job);

    /**
     * 获取Job
     *
     * @param job
     */
    JSONObject queryJob(JobEntity job);

}
