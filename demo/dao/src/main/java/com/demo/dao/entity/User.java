package com.demo.dao.entity;

import lombok.Data;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/6/20 14:50
 * @description
 **/
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private String gender;
    private String loginName;
    private String email;
    private String phone;
}
