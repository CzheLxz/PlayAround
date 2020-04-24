package com.czhe.sysmanage.annotation;


import com.czhe.sysmanage.entity.MSG_TYPE;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgTypeHandler {

    MSG_TYPE value();
}
