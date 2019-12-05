package com.demo.service.impl;

import com.demo.dao.entity.Person;
import com.demo.dao.mapper.PersonDao;
import com.demo.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/23 15:35
 * @description
 **/
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public int insert(Person person) {
        return personDao.insert(person);
    }

    @Override
    public PageInfo<Person> findAllPerson(int page, int size) {
        PageHelper.startPage(page,size);
        List<Person> personList = personDao.findAll();
        PageInfo result = new PageInfo(personList);
        return result;
    }
}
