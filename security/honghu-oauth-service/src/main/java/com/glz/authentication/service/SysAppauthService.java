package com.glz.authentication.service;

import com.oauth.entity.SysAppauth;

/**
 * @author czhe
 */
public interface SysAppauthService {

    SysAppauth findSysAppauthByclientId(String clientId);

    SysAppauth FindByClientIdAndClientSecret(SysAppauth sysAppauth);

}
