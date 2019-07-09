package com.glz.authentication.oauth.services;

import java.util.ArrayList;
import java.util.Collection;

import com.glz.authentication.service.AuthShopuserService;
import com.glz.authentication.utils.VerifyUtils;
import com.oauth.entity.AuthShopuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 获取用户接口
 *
 * @author czhe
 */
@Component
public class GLZUserDetailsService implements UserDetailsService {


    @Autowired
    private AuthShopuserService authShopuserService;

    /**
     * 根据用户名获取用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String sndchnlno = username.substring(0, 3);//获取登录渠道
        String loginType = username.substring(3, 5);//登录方式
        String endparam = username.substring(5);

        if (!VerifyUtils.verifySndChnlNo(sndchnlno)) {
            return null;
        }
        if (!VerifyUtils.verifyLoginType(loginType)) {
            return null;
        }

        AuthShopuser authShopuser = authShopuserService.getAuthShopuser(sndchnlno, loginType, endparam);

        if (loginType.equals("MB")) {
            String msgCode = "";//获取缓存里面的验证码
            return createUser(authShopuser.getUsername(), msgCode, authShopuser.getAuthorities(), authShopuser);
        }

        if (!StringUtils.isEmpty(authShopuser)) {
            return createUser(authShopuser.getUsername(), authShopuser.getPassword(), authShopuser.getAuthorities(), authShopuser);
        }

        return null;
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @param scope    权限，多个权限用逗号隔开
     * @return
     */
    public static GLZUser createUser(String username, String password, String scope, AuthShopuser authShopuser) {
        String[] split = scope.split(",");
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        for (String s : split) {
            list.add(new GrantedAuthority() {

                @Override
                public String getAuthority() {
                    return s;
                }
            });
        }
        GLZUser glzUser = new GLZUser(username, password, list);
        glzUser.setAuthShopuser(authShopuser);
        return glzUser;
    }

    /**
     * 用户类
     *
     * @author czhe
     */
    public static class GLZUser extends User {

        public GLZUser(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> scope) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, scope);
        }

        public GLZUser(String username, String password, Collection<? extends GrantedAuthority> scope) {
            super(username, password, true, true, true, true, scope);
        }

        private AuthShopuser authShopuser;

        public AuthShopuser getAuthShopuser() {
            return authShopuser;
        }

        public void setAuthShopuser(AuthShopuser authShopuser) {
            this.authShopuser = authShopuser;
        }

        /**
         *
         */
        private static final long serialVersionUID = 112L;

    }


}
