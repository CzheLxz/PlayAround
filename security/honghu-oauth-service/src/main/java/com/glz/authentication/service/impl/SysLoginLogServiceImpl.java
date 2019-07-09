package com.glz.authentication.service.impl;

import com.glz.authentication.service.SysLoginLogService;
import com.oauth.dao.SysLoginLogMapper;
import com.oauth.entity.SysLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "sysLoginLogService")
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;


    @Override
    public int insertSelective(SysLoginLog sysLoginLog) {
        return sysLoginLogMapper.insertSelective(sysLoginLog);
    }

    @Override
    public int insert(SysLoginLog sysLoginLog) {
        return sysLoginLogMapper.insert(sysLoginLog);
    }
}
