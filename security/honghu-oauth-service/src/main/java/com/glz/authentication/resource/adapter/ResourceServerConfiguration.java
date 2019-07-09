package com.glz.authentication.resource.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.glz.authentication.resource.ResourceProperties;
import com.glz.authentication.resource.exception.handle.AuthExceptionEntryPoint;
import com.glz.authentication.resource.exception.handle.CustomAccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    @Autowired
    AuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 资源配置信息
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(ResourceProperties.RESOURCE_ID).authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);

    }

    /**
     * 资源访问请求控制
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test/*").permitAll()
                .anyRequest().permitAll();
    }
}