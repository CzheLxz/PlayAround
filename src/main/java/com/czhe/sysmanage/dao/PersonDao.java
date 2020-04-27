package com.czhe.sysmanage.dao;

import com.czhe.sysmanage.entity.Person;

import java.util.List;

public interface PersonDao {

    int deleteByPrimaryKey(String id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    List<Person> findAll();


}