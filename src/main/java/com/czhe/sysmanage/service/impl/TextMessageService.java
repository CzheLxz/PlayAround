package com.czhe.sysmanage.service.impl;

import com.czhe.sysmanage.annotation.MsgTypeHandler;
import com.czhe.sysmanage.entity.MSG_TYPE;
import com.czhe.sysmanage.entity.MessageInfo;
import com.czhe.sysmanage.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 10:40
 * @description
 **/
@Service
@MsgTypeHandler(value = MSG_TYPE.TEXT)
public class TextMessageService implements MessageService {

    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理文本消息: " + messageInfo.getMessage());
    }
}
