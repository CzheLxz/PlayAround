package com.czhe.sysmanage.dao;

import com.czhe.sysmanage.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonDao {

    int insert(Person person);

    List<Person> findAll();

    int delete(String id);

}
