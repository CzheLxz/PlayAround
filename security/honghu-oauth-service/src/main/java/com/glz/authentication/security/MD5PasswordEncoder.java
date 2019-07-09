package com.glz.authentication.security;

import com.glz.authentication.utils.Md5Encrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码md5加密判断
 *
 * @author czhe
 */
@Component
public class MD5PasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }


    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(Md5Encrypt.md5(charSequence.toString()));
    }
}