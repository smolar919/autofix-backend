package com.kamilsmolarek.autofix.login.auth;

import com.kamilsmolarek.autofix.login.pass.auth.AuthPassUserProps;

public class AuthPassUserPropsMock implements AuthPassUserProps {
    @Override
    public long getResetPasswordTokenExpirationInMinutes() {
        return 15;
    }

    @Override
    public String getSysAdminFirstName() {
        return "admin";
    }

    @Override
    public String getSysAdminLastName() {
        return "admin";
    }

    @Override
    public String getSysAdminEmail() {
        return "admin@test.pl";
    }

    @Override
    public String getSysAdminPassword() {
        return "password123";
    }
}
