package com.kamilsmolarek.autofix.login.pass.auth.security;

import com.kamilsmolarek.autofix.login.pass.auth.TokenProvider;
import com.kamilsmolarek.autofix.security.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class TokenProviderAdapter implements TokenProvider {

    private final JwtUtil jwtUtil;


    public TokenProviderAdapter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String generateToken(String userId) {
        return jwtUtil.generateToken(userId);
    }
}
