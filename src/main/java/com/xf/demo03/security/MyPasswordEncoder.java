package com.xf.demo03.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xfgg
 * 用于执行密码的单向转换，以便安全地存储密码
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
