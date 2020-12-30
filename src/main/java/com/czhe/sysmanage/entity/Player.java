package com.czhe.sysmanage.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/30 16:22
 * @description
 **/
@Data
@AllArgsConstructor
public class Player {

    private String name;
    private int type;
    private List<String> cards;
    private Integer integral;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}