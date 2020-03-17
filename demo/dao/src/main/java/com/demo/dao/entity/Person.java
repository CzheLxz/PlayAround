package com.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/23 15:28
 * @description
 **/
@AllArgsConstructor
@Data
public class Person {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String job;

    @NotBlank
    private String gender;

    @Min(value = 1)
    @Max(value = 70)
    private int age;


    @Min(value = 1000)
    @Max(value = 100000)
    private int salary;
}
