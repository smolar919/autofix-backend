package com.kamilsmolarek.autofix.login.pass.auth.repository;

import com.kamilsmolarek.autofix.login.pass.auth.AuthPassUser;
import com.kamilsmolarek.autofix.login.pass.auth.ResetPasswordRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository {
    Optional<AuthPassUser> findByEmail(String email);

    Optional<AuthPassUser> findById(String id);

    Optional<ResetPasswordRequest> findByRequestId(String id);

    void saveAuthPassUser(AuthPassUser user);

    void saveResetPasswordRequest(ResetPasswordRequest request);
}

