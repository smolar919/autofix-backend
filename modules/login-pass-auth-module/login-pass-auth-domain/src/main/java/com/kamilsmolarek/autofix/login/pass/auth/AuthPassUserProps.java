package com.kamilsmolarek.autofix.login.pass.auth;

public interface AuthPassUserProps {
    long getResetPasswordTokenExpirationInMinutes();
    String getSysAdminFirstName();
    String getSysAdminLastName();
    String getSysAdminEmail();
    String getSysAdminPassword();
}