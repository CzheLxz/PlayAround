package com.glz.authentication.oauth.exception.handle;

import java.io.IOException;
import java.util.Map.Entry;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Serializer;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GLZOAuth2ExceptionJackson2Serializer extends OAuth2ExceptionJackson2Serializer {

    @Override
    public void serialize(OAuth2Exception value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("error", value.getOAuth2ErrorCode());
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        // 忽略错误描述
        // jgen.writeStringField("error_description", errorMessage);
        if (value.getAdditionalInformation() != null) {
            for (Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }

}
