package com.glz.authentication.oauth.adapter;

import java.util.Map;

import com.glz.authentication.oauth.services.GLZUserDetailsService.GLZUser;
import com.oauth.entity.AuthShopuser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class GLZDefaultAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {

        //刷新Token成功
        Map<String, Object> convertAccessToken = (Map<String, Object>) super.convertAccessToken(token, authentication);
        GLZUser principal = (GLZUser) authentication.getUserAuthentication().getPrincipal();
        AuthShopuser authShopuser = principal.getAuthShopuser();
        convertAccessToken.put("code", "EO0000");
        return convertAccessToken;
    }


}
