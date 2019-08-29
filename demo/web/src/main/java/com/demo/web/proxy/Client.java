package com.demo.web.proxy;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/29 11:09
 * @description 客户端
 **/
public class Client {
    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        UserManager userManager = (UserManager) cgLibProxy.createProxyObject(new UserManagerImpl());
        userManager.addUser("001", "abc");

        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerjdk = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerjdk.addUser("002", "xzy");

    }
}
