package com.demo.dao.mapper;

import com.demo.dao.entity.Person;

import java.util.List;

public interface PersonDao {

    int insert(Person person);

    List<Person> findAll();
}
