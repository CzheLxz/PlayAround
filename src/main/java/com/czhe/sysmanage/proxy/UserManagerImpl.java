package com.czhe.sysmanage.proxy;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/29 10:36
 * @description 被代理的实现类
 **/
public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String id, String password) {
        System.out.println("==调用了UserManager的: addUser方法==");
    }
}
