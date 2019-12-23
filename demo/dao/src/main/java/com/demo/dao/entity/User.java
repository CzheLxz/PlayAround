package com.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/12/11 16:36
 * @description
 **/
@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private String roleName;
    private boolean locked;
}
