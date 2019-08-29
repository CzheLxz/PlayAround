package com.demo.web.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/29 10:54
 * @description CGLIB 动态代理
 **/
public class CGLibProxy implements MethodInterceptor {

    private Object targetObject;

    //创建代理对象
    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        //过滤方法
        if ("addUser".equals(method.getName())) {
            checkPopedom();
        }
        obj = method.invoke(targetObject, args);
        return obj;
    }

    private void checkPopedom() {
        System.out.println("======检查权限=======");
    }


}
