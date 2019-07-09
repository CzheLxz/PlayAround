package com.glz.authentication.oauth.aop;

import com.alibaba.fastjson.JSON;
import com.glz.authentication.service.SysLogService;
import com.glz.authentication.utils.VerifyUtils;
import com.oauth.entity.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @author czhe
 * 切面处理类
 */
@Component
@Aspect
@Configuration
public class SysLogAspect {

    protected Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    //切点 在自定义注解位置切入代码
    @Pointcut("@annotation(com.glz.authentication.oauth.aop.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void insertSysLog(JoinPoint joinPoint) {

        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Start logging>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog log = method.getAnnotation(MyLog.class);
        if (log != null) {
            String value = log.value();
            sysLog.setTitle(value);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();

        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setRequestUri(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        //获取IP
        sysLog.setRemoteAddr(request.getRemoteAddr());
        sysLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        sysLog.setType("系统日志");
        sysLog.setCreateBy("admin");
        sysLog.setCreateDate(new Date());
        //保存到数据库
        sysLogService.insertSelective(sysLog);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Logging succeeded>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
