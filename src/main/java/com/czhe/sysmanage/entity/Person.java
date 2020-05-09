package com.czhe.sysmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = -5033892775591043830L;

    private String id;

    private String name;

    private String job;

    private String gender;

    private Integer age;

    private Integer salary;


}