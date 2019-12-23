package com.demo.core.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/12/11 17:36
 * @description ehcache创建配置类
 **/
@Configuration
//启动缓存
@EnableCaching
public class CacheConfiguration {

    //ehcache 主要管理器
    @Bean("ehcacheCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        System.setProperty(CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY, "true");
        return new EhCacheCacheManager(bean.getObject());
    }

    //Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("application.yml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }


}
