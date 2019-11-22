package com.demo.service.impl;

import com.demo.aspect.MsgTypeHandler;
import com.demo.dao.entity.MSG_TYPE;
import com.demo.dao.entity.MessageInfo;
import com.demo.service.MessageService;
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
