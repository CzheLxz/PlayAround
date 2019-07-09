package com.glz.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

/**
 * spring security安全配置类
 *
 * @author czhe
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
// 调整安全规则排序情况，让WebSecurityConfigurerAdapter在ResourceServerConfigurerAdapter后面
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 重新http安全管理
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth/**").permitAll().antMatchers("/oa/**").permitAll().anyRequest()
                .permitAll();
        http.csrf().disable();
    }

    /**
     * 引出管理对象
     */
    @Bean
    @Primary
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService);
        // auth.authenticationProvider(authenticationProvider);

        // auth.userDetailsService(userDetailsService).passwordEncoder(new
        // MyPasswordEncoder());
        super.configure(auth);
    }

    // @Autowired
    // UserDetailsService userDetailsService;

}