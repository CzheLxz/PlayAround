package com.glz.authentication.service.impl;

import com.glz.authentication.service.SysAppauthService;
import com.oauth.dao.SysAppauthMapper;
import com.oauth.entity.SysAppauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author czhe
 */
@Service(value = "sysAppauthService")
public class SysAppauthServiceImpl implements SysAppauthService {


    @Autowired
    private SysAppauthMapper sysAppauthMapper;


    @Override
    public SysAppauth findSysAppauthByclientId(String clientId) {
        return sysAppauthMapper.findSysAppauthByclientId(clientId);
    }

    @Override
    public SysAppauth FindByClientIdAndClientSecret(SysAppauth sysAppauth) {
        return sysAppauthMapper.FindByClientIdAndClientSecret(sysAppauth);
    }

}
