package com.kamilsmolarek.autofix.login.auth;

import com.kamilsmolarek.autofix.login.pass.auth.PasswordEncoder;

import java.util.Objects;

public class PasswordEncoderMock implements PasswordEncoder {

    @Override
    public String encode(String rawText) {
        return rawText;
    }

    @Override
    public boolean match(String rawText, String encoded) {
        return Objects.equals(rawText, encoded);
    }
}
