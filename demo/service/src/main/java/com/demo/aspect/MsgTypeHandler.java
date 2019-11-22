package com.demo.aspect;

import com.demo.dao.entity.MSG_TYPE;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgTypeHandler {

    MSG_TYPE value();
}
