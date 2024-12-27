package com.kamilsmolarek.autofix.login.pass.auth.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.login.pass.auth.*;
import com.kamilsmolarek.autofix.login.pass.auth.forms.*;
import com.kamilsmolarek.autofix.login.pass.auth.repository.AuthorizationRepository;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.forms.CreateUserForm;
import com.kamilsmolarek.autofix.user.management.service.UserManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final UserManagementService userManagementService;

    private final AuthPassUserProps props;


    public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, UserManagementService userManagementService, AuthPassUserProps props) {
        this.authorizationRepository = authorizationRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userManagementService = userManagementService;
        this.props = props;
    }

    private AuthPassUser getByEmailOrThrowAuthPassUser(String email) {
        return authorizationRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    private AuthPassUser getByIdOrThrowAuthPassUser(String id) {
        return authorizationRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }
    private ResetPasswordRequest getOrThrowResetPasswordRequest(String id) {
        return authorizationRepository.findByRequestId(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public LoginResponse login(LoginForm loginRequest) {
        AuthPassUser user = getByEmailOrThrowAuthPassUser(loginRequest.getEmail());
        User userDetails = userManagementService.get(user.getId());

        if (userDetails.isBlocked()) {
            throw new ApplicationException(ErrorCode.USER_IS_BLOCKED);
        }

        if (passwordEncoder.match(loginRequest.getPassword(), user.getPassword())) {
            String token = tokenProvider.generateToken(user.getId());
            return new LoginResponse(token);
        }
        throw new ApplicationException(ErrorCode.WRONG_LOGIN_OR_PASSWORD);
    }

    @Override
    public void createUser(CreateUserWithPasswordForm createUserRequest, String createdById) {

        User user = userManagementService.save(createUserRequest, createdById);

        AuthPassUser userAuth = AuthPassUser.builder()
                .id(user.getId())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .build();


        authorizationRepository.saveAuthPassUser(userAuth);
    }


    @Override
    public void resetPassword(ResetPasswordForm resetPasswordRequest) {
        Instant time = Instant.now();
        AuthPassUser authUser = getByEmailOrThrowAuthPassUser(resetPasswordRequest.getEmail());
        User user = userManagementService.get(authUser.getId());
        ResetPasswordRequest request = ResetPasswordRequest.builder()
                .id(UUID.randomUUID().toString())
                .createdOn(time)
                .expiredOn(time.plus(props.getResetPasswordTokenExpirationInMinutes(), ChronoUnit.MINUTES))
                .userId(authUser.getId())
                .build();

        authorizationRepository.saveResetPasswordRequest(request);
    }

    @Override
    public void checkResetPasswordToken(String token) {
        ResetPasswordRequest request = getOrThrowResetPasswordRequest(token);
        if (!request.getExpiredOn().isAfter(Instant.now())) {
            throw new ApplicationException(ErrorCode.LINK_EXPIRED);
        }
    }

    @Override
    public void resetPasswordConfirm(ResetPasswordConfirmForm resetPasswordConfirmForm) {
        ResetPasswordRequest request = getOrThrowResetPasswordRequest(resetPasswordConfirmForm.getRequestId());
        AuthPassUser user = getByIdOrThrowAuthPassUser(request.getUserId());

        checkResetPasswordToken(resetPasswordConfirmForm.getRequestId());

        user.setPassword(passwordEncoder.encode(resetPasswordConfirmForm.getPassword()));
        authorizationRepository.saveAuthPassUser(user);
    }

    @Override
    public void changePassword(ChangePasswordForm changePasswordForm) {
        AuthPassUser user = getByEmailOrThrowAuthPassUser(changePasswordForm.getEmail());

        if (passwordEncoder.match(changePasswordForm.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
            authorizationRepository.saveAuthPassUser(user);
        } else {
            throw new ApplicationException(ErrorCode.WRONG_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void deleteUser(String userId) {
        User user = userManagementService.get(userId);
    }

    @Override
    public void logout(String userId) {
        User user = userManagementService.get(userId);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void createSysAdminOnStartup() {

        if (authorizationRepository.findByEmail(props.getSysAdminEmail()).isPresent()) {
            log.info("Method createSysAdminOnStartup, SysAdmin already exists");
            return;
        }

        User user = userManagementService.saveSysAdminOnStartup(new CreateUserForm(props.getSysAdminFirstName(), props.getSysAdminLastName(), props.getSysAdminEmail()));

        AuthPassUser userAuth = AuthPassUser.builder()
                .id(user.getId())
                .email(props.getSysAdminEmail())
                .password(passwordEncoder.encode(props.getSysAdminPassword()))
                .build();

        authorizationRepository.saveAuthPassUser(userAuth);
    }
}