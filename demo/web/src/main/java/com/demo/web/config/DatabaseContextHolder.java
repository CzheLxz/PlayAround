package com.demo.web.config;

import com.demo.dao.entity.DatabaseType;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/12/10 17:19
 * @description 保存一个线程安全的DatabaseType容器
 **/
public class DatabaseContextHolder {

    // 用于存放多线程环境下的成员变量
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal <>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getContextHolder() {
        return contextHolder.get();
    }
}
