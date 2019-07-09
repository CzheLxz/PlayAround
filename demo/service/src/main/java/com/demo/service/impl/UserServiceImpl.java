package com.demo.service.impl;

import com.demo.dao.entity.User;
import com.demo.dao.mapper.UserDao;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/6/20 15:09
 * @description 用户接口实现类
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }
}
