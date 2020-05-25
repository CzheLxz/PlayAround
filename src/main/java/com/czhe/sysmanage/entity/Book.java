package com.czhe.sysmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = -7651290068904762624L;

    private String id;
    private String name;

}
