package com.glz.authentication.oauth.exception.handle;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson1Deserializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson1Serializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Deserializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Serializer;

@org.codehaus.jackson.map.annotate.JsonSerialize(using = GLZOAuth2ExceptionJackson1Serializer.class)
@org.codehaus.jackson.map.annotate.JsonDeserialize(using = OAuth2ExceptionJackson1Deserializer.class)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = GLZOAuth2ExceptionJackson2Serializer.class)
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = OAuth2ExceptionJackson2Deserializer.class)
public class GLZOAuth2Exception extends OAuth2Exception {

    public String errorCode;

    public GLZOAuth2Exception(String msg) {
        super(msg);
        addAdditionalInformation("error_msg", msg);
        errorCode = "404";
    }

    public GLZOAuth2Exception(String msg, String errorCode) {
        super(msg);
        addAdditionalInformation("error_msg", msg);
        this.errorCode = errorCode;
    }

    @Override
    public String getOAuth2ErrorCode() {
        return this.errorCode;
    }

    /**
     *
     */
    private static final long serialVersionUID = 111L;

}
