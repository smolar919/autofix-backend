package com.kamilsmolarek.autofix.login.pass.auth;

public interface TokenProvider {
    String generateToken(String userId);
}
