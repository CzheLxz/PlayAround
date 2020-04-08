package com.demo.web.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/4/8 10:23
 * @description 修改database
 **/
@Configuration
@AutoConfigureAfter(RedisCacheAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate  = new RedisTemplate<>();
        redisTemplate .setKeySerializer(new StringRedisSerializer());
        redisTemplate .setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate .setConnectionFactory(redisConnectionFactory);
        return redisTemplate ;
    }
}

