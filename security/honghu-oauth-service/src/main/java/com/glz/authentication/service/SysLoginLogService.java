package com.glz.authentication.service;

import com.oauth.entity.SysLoginLog;

/**
 * @author czhe
 */
public interface SysLoginLogService {

    int insertSelective(SysLoginLog sysLoginLog);

    int insert(SysLoginLog sysLoginLog);
}
