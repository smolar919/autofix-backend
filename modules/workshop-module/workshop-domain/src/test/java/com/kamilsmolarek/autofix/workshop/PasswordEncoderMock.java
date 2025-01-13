package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.login.pass.auth.PasswordEncoder;

import java.util.Objects;

public class PasswordEncoderMock implements PasswordEncoder {

    @Override
    public String encode(String rawText) {
        if (rawText == null) {
            throw new IllegalArgumentException("rawText cannot be null");
        }
        return rawText + "-encoded";
    }

    @Override
    public boolean match(String rawText, String encoded) {
        if (rawText == null || encoded == null) {
            return false;
        }
        return (rawText + "-encoded").equals(encoded);
    }

}
