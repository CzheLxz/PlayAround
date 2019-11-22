package com.demo.service;

import com.demo.dao.entity.MessageInfo;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 10:36
 * @description
 **/
public interface MessageService {

    void handleMessage(MessageInfo messageInfo);
}
