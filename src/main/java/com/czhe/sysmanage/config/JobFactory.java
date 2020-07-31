package com.czhe.sysmanage.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/7/31 11:48
 * @description job实例工厂 使用默认的可能存在spring @Autowired 无法注入问题
 **/
@Component
public class JobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        //Job实例注入到Job工厂
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}