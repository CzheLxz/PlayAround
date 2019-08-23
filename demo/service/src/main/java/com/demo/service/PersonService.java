package com.demo.service;

import com.demo.dao.entity.Person;

import java.util.List;

public interface PersonService {

    int insert(Person person);

    List<Person> findAll();
}
