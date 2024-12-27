package com.kamilsmolarek.autofix.management;


import com.kamilsmolarek.autofix.commons.LoggedUser;

public class LoggedUserMock implements LoggedUser {
    @Override
    public String getUserId() {
        return "user_id";
    }
}
