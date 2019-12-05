package com.demo.dao.mapper;

import com.demo.dao.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component//处理Service层注入报红,可以不加因为在启动类加了扫描demo包下所有的类
public interface PersonDao {

    int insert(Person person);

    List<Person> findAll();
}
