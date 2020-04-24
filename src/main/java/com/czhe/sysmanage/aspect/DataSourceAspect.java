package com.czhe.sysmanage.aspect;

import com.czhe.sysmanage.config.DatabaseContextHolder;
import com.czhe.sysmanage.config.DynamicDataSource;
import com.czhe.sysmanage.entity.DatabaseType;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/12/11 10:12
 * @description 动态处理数据源 根据命名区分
 **/
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {

    private static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("execution(* com.czhe.sysmanage.dao.*.*(..))") //切点
    public void aspect() {

    }

    @Before("aspect()")
    public void before(JoinPoint point) { //在指定切点的方法之前执行
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        String args = StringUtils.join(point.getArgs(), ",");
        logger.info(">>>>>>>>>>>>>>>> className:{},method:{},args:{}", className, method, args);
        try {
            for (DatabaseType type : DatabaseType.values()) {
                List<String> values = DynamicDataSource.METHOD_TYPE_MAP.get(type);
                values.forEach(value -> {
                    if (method.startsWith(value)) {
                        logger.info(">>>>>>>>>>>>>>>> {} 方法开头包含的单词为: {}", method, value);
                        DatabaseContextHolder.setDatabaseType(type);
                        DatabaseType types = DatabaseContextHolder.getContextHolder();
                        logger.info(">>>>>>>>>>>>>>>> {} 方法使用的数据源为: {}", method, types);
                    }
                });
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
