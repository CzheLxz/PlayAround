package com.glz.authentication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RestController;

@MapperScan("com.oauth.dao")
@SpringBootApplication
@RestController
@ServletComponentScan
public class OAuth {
    public static void main(String[] args) {
        SpringApplication.run(OAuth.class, new String[]{"debug"});
    }
}
