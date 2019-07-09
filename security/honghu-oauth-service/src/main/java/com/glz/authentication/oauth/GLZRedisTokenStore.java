package com.glz.authentication.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

/**
 * token存储接口，采用redis存储
 *
 * @author czhe
 */
@Component
public class GLZRedisTokenStore extends RedisTokenStore {

    @Autowired
    public GLZRedisTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

}
