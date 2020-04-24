package com.czhe.sysmanage.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum DatabaseType {

    master("write"), slave("read");
    private String name;
}
