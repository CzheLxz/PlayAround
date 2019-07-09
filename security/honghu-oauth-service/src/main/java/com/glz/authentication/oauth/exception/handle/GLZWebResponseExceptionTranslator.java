package com.glz.authentication.oauth.exception.handle;

import com.glz.authentication.oauth.filters.TokenFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Component
public class GLZWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        GLZOAuth2Exception glzoAuth2Exception = new GLZOAuth2Exception(e.getMessage(), "oauth认证错误");
        glzoAuth2Exception.addAdditionalInformation("code", "EO0009");


        //记录登录失败日志
        TokenFilter.AuthMessage object = TokenFilter.t.get();

        ResponseEntity<OAuth2Exception> r = new ResponseEntity<OAuth2Exception>(
                glzoAuth2Exception, HttpStatus.NOT_FOUND);
        return r;
    }

}
