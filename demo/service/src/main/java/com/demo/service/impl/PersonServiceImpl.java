package com.demo.service.impl;

import com.demo.dao.entity.Person;
import com.demo.dao.mapper.PersonDao;
import com.demo.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        PageHelper.startPage(page, size);
        List<Person> personList = personDao.findAll();
        //先根据人员性别排序再根据职业排序再按薪水降序
        String[] orderGender = new String[]{"male", "female", "unknown"};
        String[] orderJob = new String[]{"python", "java", "php", "H5"};
        personList = personList.stream().sorted(
                Comparator.comparing(Person::getGender, (x, y) -> {
                    return orderPerson(orderGender, x, y);
                }).thenComparing(Comparator.comparing(Person::getJob, (x, y) -> {
                    return orderPerson(orderJob, x, y);
                })).thenComparing(Comparator.comparing(Person::getSalary).reversed())
        ).collect(Collectors.toList());//forEach(System.out::println)
        PageInfo result = new PageInfo(personList);
        return result;
    }

    @Override
    public int delete(String id) {
        return personDao.delete(id);
    }

    /**
     * 排序方法
     *
     * @param orderby 自定义排序数组
     * @param x
     * @param y
     * @return
     */
    public int orderPerson(String[] orderby, String x, String y) {
        /*//字段值存在空的情况 if(x == null && y != null){
            return 1;
        }else if(x !=null && y == null){
            return -1;
        }else if(x == null && y == null){
            return -1;
        }else{}*/
        for (String by : orderby) {
            if (by.equals(x) || by.equals(y)) {
                if (x.equals(y)) {
                    return 0;
                } else if (by.equals(x)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }
}
