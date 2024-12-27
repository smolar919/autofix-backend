package com.kamilsmolarek.autofix.login.pass.auth.security;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.security.JwtAuthUser;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.repository.UserManagementRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserManagementRepository repository;


    public CustomUserDetailsService(UserManagementRepository repository) {
        this.repository = repository;
    }

    private User getOrThrow(String id) {
        return repository.getNotDeleted(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = getOrThrow(id);
        return JwtAuthUser.builder()
                .id(user.getId())
                .locked(user.isBlocked())
                .build();
    }
}

