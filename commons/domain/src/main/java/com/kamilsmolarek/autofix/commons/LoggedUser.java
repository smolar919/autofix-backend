package com.kamilsmolarek.autofix.commons;

import org.springframework.stereotype.Component;

@Component
public interface LoggedUser {
    String getUserId();
}
