package com.czhe.sysmanage.paramCheck;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 15:06
 * @description
 **/
@Component
@Aspect
public class ParamValidAop {


    //切入点
    @Pointcut("execution(public * com.czhe.sysmanage.controller..*.*(..))")
    public void checkParam() {
    }

    @Before("checkParam()")
    public void doBefore(JoinPoint joinPoint) {
    }

    //检查参数是否为空
    @Around("checkParam()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = ((MethodSignature) pjp.getSignature());
        //拦截的方法
        Method method = signature.getMethod();
        //获取方法参数注解 返回二维数组是因为某些参数可能存在多个注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            pjp.proceed();
        }
        //获取方法参数名
        String[] paramNames = signature.getParameterNames();
        //参数值
        Object[] paramValues = pjp.getArgs();
        //获取方法参数类型
        Class<?>[] paramTypes = signature.getParameterTypes();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                //如果该参数前面的注解不为空并且是ParamCheck的实例 并且notNull() = true 且默认值为空 则进行非空校验
                if (parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof ParamCheck && ((ParamCheck) parameterAnnotations[i][j]).notNull() && StringUtils.isEmpty(((ParamCheck) parameterAnnotations[i][j]).defaultValue())) {
                    paramIsNull(paramNames[i], paramValues[i], paramTypes[i] == null ? null : paramTypes[i].getTypeName());
                    break;
                }
                //如果该参数前面的注解不为空并且是ParamCheck的实例 并且默认值不为空 且参数值为空 则进行赋默认值
                if (parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof ParamCheck && !StringUtils.isEmpty(((ParamCheck) parameterAnnotations[i][j]).defaultValue()) && (paramValues[i] == null || StringUtils.isEmpty(paramValues[i].toString()))) {
                    paramValues[i] = putParam(((ParamCheck) parameterAnnotations[i][j]).defaultValue(), paramTypes[i]);
                }
            }

        }

        return pjp.proceed(paramValues);

    }

    //在切入点返回内容之后切入内容(用来对处理返回值做一些加工处理)
    @AfterReturning("checkParam()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }

    /**
     * 参数非空校验 如果为空抛出ParamIsNullException异常
     *
     * @param paramName
     * @param value
     * @param parameterType
     */
    private void paramIsNull(String paramName, Object value, String parameterType) {
        if (value == null || "".equals(value.toString().trim())) {
            throw new ParamIsNullException(paramName, parameterType, "参数为空");
        }
    }

    private Object putParam(Object value, Class<?> parameterType) {
        return CastValueTypeUtil.parseValue(parameterType, value.toString());
    }

}