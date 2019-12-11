package com.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum DatabaseType {

    master("read"), slave("write");
    private String name;
}
