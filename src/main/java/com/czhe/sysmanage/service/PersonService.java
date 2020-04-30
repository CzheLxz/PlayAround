package com.czhe.sysmanage.service;


import com.czhe.sysmanage.entity.Person;
import com.github.pagehelper.PageInfo;

public interface PersonService {

    int insert(Person person);

    PageInfo<Person> findAllPerson(int page, int size);

    int delete(String id);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person person);


}
