package com.oauth.dao;

import com.oauth.entity.SysAppauth;

/**
 * @author czhe
 */
public interface SysAppauthMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysAppauth record);

    int insertSelective(SysAppauth record);

    SysAppauth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAppauth record);

    int updateByPrimaryKey(SysAppauth record);

    //根据clientId查询权限
    SysAppauth findSysAppauthByclientId(String clientId);

    //根据id secret查询
    SysAppauth FindByClientIdAndClientSecret(SysAppauth sysAppauth);
}