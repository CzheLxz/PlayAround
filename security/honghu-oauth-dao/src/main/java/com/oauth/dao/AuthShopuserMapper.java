package com.oauth.dao;

import com.oauth.entity.AuthShopuser;
import org.apache.ibatis.annotations.Param;

/**
 * @author czhe
 */
public interface AuthShopuserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AuthShopuser record);

    int insertSelective(AuthShopuser record);

    AuthShopuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthShopuser record);

    int updateByPrimaryKey(AuthShopuser record);

    //根据手机号查询用户
    AuthShopuser selectByPrimaryKeyMobile(String mobile);

    //根据登录方式查询授权用户
    AuthShopuser getAuthShopuser(@Param("sndchnlno") String sndchnlno, @Param("loginType") String loginType, @Param("endparam") String endparam);

    //根据用户名查询用户
    AuthShopuser findByUserName(String userName);

    //修改第三方账号
    int updateData(@Param("authShopuser") AuthShopuser authShopuser);

    //查询账号是否有第三方绑定
    String selectBindAccountState(@Param("userId") Integer userId, @Param("type") String type);
}