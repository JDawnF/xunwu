package com.baichen.xunwu.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Date: 2019-11-30 22:51
 * @Author: baichen
 * @Description SB2.X开始需要增加PasswordEncoder实现类，因为引用了Spring Security5.x
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword);
    }
}
