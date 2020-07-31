package com.czhe.sysmanage.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/7/31 11:35
 * @description 定时任务用到的参数
 **/
@Data
@ToString
public class JobEntity {
    private String jobId; //唯一id
    private String className; //定时任务示例的 class路径
    private String cronExpression; //cron表达式
    private String jobName; //任务名称
    private String jobGroup; //所属组
    private String triggerName; //触发器名称
    private String trigerGroup; //触发器组
    private String description; //备注
    private JSONObject data; //携带参数

    /**
     * 预留的数据库字段 如果任务信息选择手动自己存入数据库的话,会使用到
     */
    private Boolean pauseStatus;  //是否暂停
    private Boolean deleteStatus; //是否有效
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

}