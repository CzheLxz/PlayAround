package com.demo.service;

import com.demo.dao.entity.Person;
import com.github.pagehelper.PageInfo;


public interface PersonService {

    int insert(Person person);

    PageInfo<Person> findAllPerson(int page, int size);

    int delete(String id);
}
