package com.kamilsmolarek.autofix.login.pass.auth;

import com.kamilsmolarek.autofix.commons.LoggedUser;
import com.kamilsmolarek.autofix.login.pass.auth.forms.CreateUserWithPasswordForm;
import com.kamilsmolarek.autofix.login.pass.auth.forms.LoginForm;
import com.kamilsmolarek.autofix.login.pass.auth.forms.ResetPasswordConfirmForm;
import com.kamilsmolarek.autofix.login.pass.auth.forms.ResetPasswordForm;
import com.kamilsmolarek.autofix.login.pass.auth.service.AuthorizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginPassAuthController {

    private final AuthorizationService authorizationService;
    private final LoggedUser loggedUser;

    public LoginPassAuthController(AuthorizationService authorizationService, LoggedUser loggedUser) {
        this.authorizationService = authorizationService;
        this.loggedUser = loggedUser;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginForm form) {
        return authorizationService.login(form);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody CreateUserWithPasswordForm form) {
        authorizationService.createUser(form, loggedUser.getUserId());
    }

    @PostMapping("/reset-password-request")
    public void resetPassword(@RequestBody ResetPasswordForm form) {
        authorizationService.resetPassword(form);
    }

    @PutMapping("/reset-password")
    public void resetPasswordConfirm(@RequestBody ResetPasswordConfirmForm form) {
        authorizationService.resetPasswordConfirm(form);
    }

    @PostMapping("/logout")
    public void logout() { authorizationService.logout(loggedUser.getUserId());}

}
