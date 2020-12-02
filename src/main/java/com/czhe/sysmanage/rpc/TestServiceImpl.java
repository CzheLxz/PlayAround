package com.czhe.sysmanage.rpc;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/2 11:35
 * @description
 **/
public class TestServiceImpl implements TestService {
    @Override
    public String getMsg(String code, String msg) {
        System.out.println(String.format("............响应码: %s 响应信息: %s",code,msg));
        return "success";
    }
}