package com.glz.authentication.service.impl;

import com.glz.authentication.service.SysLogService;
import com.oauth.dao.SysLogMapper;
import com.oauth.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "sysLogService")
public class SysLogServiceImpl implements SysLogService {


    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int insertSelective(SysLog sysLog) {
        return sysLogMapper.insertSelective(sysLog);
    }


}
