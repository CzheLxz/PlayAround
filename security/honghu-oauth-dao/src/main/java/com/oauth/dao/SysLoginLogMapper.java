package com.oauth.dao;

import com.oauth.entity.SysLoginLog;

public interface SysLoginLogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);
}