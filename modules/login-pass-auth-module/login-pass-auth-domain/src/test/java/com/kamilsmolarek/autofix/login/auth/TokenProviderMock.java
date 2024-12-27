package com.kamilsmolarek.autofix.login.auth;

import com.kamilsmolarek.autofix.login.pass.auth.TokenProvider;

import java.util.UUID;

public class TokenProviderMock implements TokenProvider {
    @Override
    public String generateToken(String userId) {
        return UUID.randomUUID().toString();
    }
}
