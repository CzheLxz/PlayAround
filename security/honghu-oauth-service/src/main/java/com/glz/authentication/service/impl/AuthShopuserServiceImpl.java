package com.glz.authentication.service.impl;

import com.glz.authentication.service.AuthShopuserService;
import com.oauth.dao.AuthShopuserMapper;
import com.oauth.entity.AuthShopuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author czhe
 */
@Service(value = "authShopuserService")
public class AuthShopuserServiceImpl implements AuthShopuserService {

    @Autowired
    private AuthShopuserMapper authShopuserMapper;


    @Override
    public AuthShopuser getAuthShopuser(String sndchnlno, String loginType, String endparam) {
        return authShopuserMapper.getAuthShopuser(sndchnlno, loginType, endparam);
    }

    @Override
    public int updateByPrimaryKeySelective(AuthShopuser record) {
        return authShopuserMapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public AuthShopuser findByUserName(String userName) {
        return authShopuserMapper.findByUserName(userName);
    }

    @Override
    public int insertSelective(AuthShopuser authShopuser) {
        return authShopuserMapper.insertSelective(authShopuser);
    }

    @Override
    public AuthShopuser selectByPrimaryKeyMobile(String mobile) {
        return authShopuserMapper.selectByPrimaryKeyMobile(mobile);
    }

    @Override
    public int updatePassword(Integer userId, String password) {
        AuthShopuser authShopuser = new AuthShopuser();
        authShopuser.setId(userId);
        authShopuser.setPassword(password);
        authShopuserMapper.updateByPrimaryKeySelective(authShopuser);
        return authShopuserMapper.updateByPrimaryKeySelective(authShopuser);
    }

    /**
     * 根据类型绑定用户对应的第三方账号
     *
     * @param authShopuser 用户信息
     * @param type         第三方类型
     * @param openId       第三方openId
     * @return
     */
    @Override
    public int updateData(AuthShopuser authShopuser, String type, String openId) {
        if (type.equals("1")) {
            authShopuser.setQqOpenid(openId);
            return authShopuserMapper.updateData(authShopuser);
        } else if (type.equals("2")) {
            authShopuser.setWxOpenid(openId);
            return authShopuserMapper.updateData(authShopuser);
        } else {
            authShopuser.setSinaOpenid(openId);
            return authShopuserMapper.updateData(authShopuser);
        }
    }

    @Override
    public String checkBindAccount(Integer userId, String type) {
        return authShopuserMapper.selectBindAccountState(userId, type);
    }


}
