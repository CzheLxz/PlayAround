package com.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 10:33
 * @description
 **/
@Data
@AllArgsConstructor
public class MessageInfo {

    //消息类型
    private Integer code;
    //消息内容
    private String message;
}
