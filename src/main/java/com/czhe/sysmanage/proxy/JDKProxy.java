package com.czhe.sysmanage.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/29 10:40
 * @description jdk 动态代理
 **/
public class JDKProxy implements InvocationHandler {

    private Object targetObject;//需要代理的目标对象

    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;
        //返回代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkPropedom();//模拟权限检查
        Object ret = null;
        ret = method.invoke(targetObject, args);
        return ret;
    }

    private void checkPropedom() {
        System.out.println("=====模拟权限检查=====");
    }
}
