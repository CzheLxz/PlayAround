package com.glz.authentication.service;

import com.oauth.entity.AuthShopuser;

/**
 * author czhe
 */
public interface AuthShopuserService {


    AuthShopuser getAuthShopuser(String sndchnlno, String loginType, String endparam);


    int updateByPrimaryKeySelective(AuthShopuser record);

    //根据用户名查询用户
    AuthShopuser findByUserName(String userName);

    //动态插入用户信息
    int insertSelective(AuthShopuser authShopuser);

    AuthShopuser selectByPrimaryKeyMobile(String mobile);

    //更新绑定密码
    int updatePassword(Integer userId, String password);

    //更新第三方绑定
    int updateData(AuthShopuser authShopuser, String type, String openId);

    String checkBindAccount(Integer userId, String type);

}
