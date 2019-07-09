package com.glz.authentication.oauth.aop;

import java.lang.annotation.*;

/**
 * @author czhe
 * @Description 自定义注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
