package com.czhe.sysmanage.rpc;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/2 10:08
 * @description 客户端接口
 **/
public interface Client<T> {

    T getService(Class<T> t);
}
