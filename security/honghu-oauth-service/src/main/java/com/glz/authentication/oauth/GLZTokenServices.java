package com.glz.authentication.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GLZTokenServices extends DefaultTokenServices {

    @Autowired
    public void setTokenStore(@Qualifier("GLZRedisTokenStore") TokenStore tokenStore) {
        super.setTokenStore(tokenStore);
    }
}
