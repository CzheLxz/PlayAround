package com.oauth.dao;

import com.oauth.entity.SysSmscodeTemplate;

public interface SysSmscodeTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysSmscodeTemplate record);

    int insertSelective(SysSmscodeTemplate record);

    SysSmscodeTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSmscodeTemplate record);

    int updateByPrimaryKeyWithBLOBs(SysSmscodeTemplate record);

    int updateByPrimaryKey(SysSmscodeTemplate record);
}