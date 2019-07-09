package com.glz.authentication.utils;

import com.glz.authentication.oauth.services.GLZUserDetailsService.GLZUser;
import com.oauth.entity.AuthShopuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Autowired
    public TokenStore tokenStore;

    @Autowired
    public DefaultTokenServices tokenServices;

    /**
     * 获取token信息
     *
     * @param access_token
     * @return
     */
    public OAuth2AccessToken getToken(String access_token) {
        OAuth2AccessToken readAccessToken = tokenStore.readAccessToken(access_token);

        return readAccessToken;
    }

    /**
     * 获取认证信息
     *
     * @param access_token
     * @return
     */
    public AuthShopuser getAuth(String access_token) {
        OAuth2Authentication loadAuthentication = tokenServices.loadAuthentication(access_token);
        if (loadAuthentication == null || loadAuthentication.getUserAuthentication() == null)
            return null;
        GLZUser principal = (GLZUser) loadAuthentication.getUserAuthentication().getPrincipal();
        if (principal == null) {
            return null;
        }
        return principal.getAuthShopuser();
    }

}
