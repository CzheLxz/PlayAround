package com.czhe.sysmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/12/11 16:36
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -281334482072906040L;
    private Long id;
    private String username;
    private String password;
    private String roleName;
    private boolean locked;
}
