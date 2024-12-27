package com.kamilsmolarek.autofix.login.pass.auth.service;

import com.kamilsmolarek.autofix.login.pass.auth.LoginResponse;
import com.kamilsmolarek.autofix.login.pass.auth.forms.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthorizationService {

    LoginResponse login(@Valid LoginForm logInRequest);

    void createUser(@Valid CreateUserWithPasswordForm createUserRequest, String createdById);

    void resetPassword(@Valid ResetPasswordForm resetPasswordRequest);

    void checkResetPasswordToken(String requestId);

    void resetPasswordConfirm(@Valid ResetPasswordConfirmForm resetPasswordConfirmForm);

    void changePassword(@Valid ChangePasswordForm changePasswordForm);

    void deleteUser(String userId);

    void logout(String userId);
}
