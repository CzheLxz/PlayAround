package com.czhe.sysmanage.paramCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 14:57
 * @description
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCheck {

    /**
     * 是否非空 默认不能为空
     */
    boolean notNull() default true;

    /**
     * 默认值
     */
    String defaultValue() default "";

}
