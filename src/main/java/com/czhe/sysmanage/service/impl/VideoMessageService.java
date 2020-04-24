package com.czhe.sysmanage.service.impl;

import com.czhe.sysmanage.annotation.MsgTypeHandler;
import com.czhe.sysmanage.entity.MSG_TYPE;
import com.czhe.sysmanage.entity.MessageInfo;
import com.czhe.sysmanage.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 11:04
 * @description
 **/
@Service
@MsgTypeHandler(MSG_TYPE.VIDEO)
public class VideoMessageService implements MessageService {

    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理视频消息: " + messageInfo.getMessage());
    }
}
