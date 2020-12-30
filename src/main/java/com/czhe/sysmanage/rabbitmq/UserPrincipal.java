package com.czhe.sysmanage.rabbitmq;

import java.security.Principal;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/16 10:15
 * @description
 **/
public class UserPrincipal implements Principal {

    private final String name;

    public UserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}