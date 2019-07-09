package com.glz.authentication.oauth.exception.handle;

import java.io.IOException;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson1Serializer;
import org.springframework.web.util.HtmlUtils;

public class GLZOAuth2ExceptionJackson1Serializer extends OAuth2ExceptionJackson1Serializer {

    @Override
    public void serialize(OAuth2Exception value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeStringField("error", value.getOAuth2ErrorCode());
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
//		jgen.writeStringField("error_description", errorMessage);
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
