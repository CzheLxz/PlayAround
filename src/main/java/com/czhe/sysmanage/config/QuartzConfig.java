package com.czhe.sysmanage.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/7/31 11:59
 * @description 调度工厂 线程池属性等配置
 **/
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;


    @Bean("schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //设置调度类quartz属性
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        //设置jobFactory
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

    /**
     * 解析quartz.properties文件 填充属性
     *
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //若不做额外配置,会有默认的配置文件加载 在jar org.quartz里面 有一份quartz.properties
        //propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     *
     * @return
     */
    @Bean
    public QuartzInitializerListener initializerListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 根据调度类工厂bean获取调度
     *
     * @return
     * @throws IOException
     */
    @Bean("scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}