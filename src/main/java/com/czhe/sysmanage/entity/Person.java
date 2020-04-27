package com.czhe.sysmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class Person {
    private String id;

    private String name;

    private String job;

    private String gender;

    private Integer age;

    private Integer salary;


}