package com.demo.dao.entity;

import lombok.Data;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/23 15:28
 * @description
 **/
@Data
public class Person {
    private String id;
    private String name;
    private String job;
    private String gender;
    private int age;
    private int salary;
}
