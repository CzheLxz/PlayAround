package com.czhe.sysmanage.rpc;

import java.io.IOException;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/2 11:56
 * @description
 **/
public class ServiceStart {
    public static void main(String[] args) throws IOException {
        new RpcServer().handler();
    }
}