package com.czhe.sysmanage.service.impl;

import com.czhe.sysmanage.dao.PersonDao;
import com.czhe.sysmanage.entity.Person;
import com.czhe.sysmanage.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired(required = false)
    private PersonDao personDao;

    @Override
    public int insert(Person person) {
        return personDao.insert(person);
    }

    @Override
    public PageInfo<Person> findAllPerson(int page, int size) {
        PageHelper.startPage(page, size);
        List<Person> personList = personDao.findAll();
        /*//先根据人员性别排序再根据职业排序再按薪水降序
        String[] orderGender = new String[]{"male", "female", "unknown"};
        String[] orderJob = new String[]{"python", "java", "C++","C","H5"};
        personList = personList.stream().sorted(
                Comparator.comparing(Person::getGender, (x, y) -> {
                    return orderPerson(orderGender, x, y);
                }).thenComparing(Comparator.comparing(Person::getJob, (x, y) -> {
                    return orderPerson(orderJob, x, y);
                })).thenComparing(Comparator.comparing(Person::getSalary).reversed())
        ).collect(Collectors.toList());//forEach(System.out::println)*/
        PageInfo result = new PageInfo(personList);
        return result;
    }

    @CacheEvict(value = "person",key = "#id")
    @Override
    public int delete(String id) {
        log.info("根据ID 删除人员信息................");
        return personDao.deleteByPrimaryKey(id);
    }

    @Cacheable(value = "person",key = "#id")
    @Override
    public Person selectByPrimaryKey(String id) {
        log.info("根据ID 查询人员信息................");
        return personDao.selectByPrimaryKey(id);
    }

    @CachePut(value = "person",key = "#person.id")
    @Override
    public int updateByPrimaryKeySelective(Person person) {
        log.info("根据ID 修改人员信息................");
        return personDao.updateByPrimaryKeySelective(person);
    }

    /**
     * 排序方法
     *
     * @param byArray 自定义排序数组
     * @param x
     * @param y
     * @return
     */
    public int orderPerson(String[] byArray, String x, String y) {
        /*//字段值存在空的情况 if(x == null && y != null){
            return 1;
        }else if(x !=null && y == null){
            return -1;
        }else if(x == null && y == null){
            return -1;
        }else{}*/
        for (String by : byArray) {
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
