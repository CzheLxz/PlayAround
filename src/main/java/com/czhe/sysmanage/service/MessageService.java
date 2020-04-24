package com.czhe.sysmanage.service;


import com.czhe.sysmanage.entity.MessageInfo;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 10:36
 * @description
 **/
public interface MessageService {

    void handleMessage(MessageInfo messageInfo);
}
