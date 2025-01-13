package com.kamilsmolarek.autofix.user.management.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.user.management.Role;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.forms.CreateUserForm;
import com.kamilsmolarek.autofix.user.management.forms.EditUserForm;
import com.kamilsmolarek.autofix.user.management.repository.UserManagementRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserManagementRepository userManagementRepository;

    public UserManagementServiceImpl(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    @Override
    public SearchResponse<User> search(SearchForm form) {
        return userManagementRepository.search(form);
    }

    private User getOrThrow(String id) {
        return userManagementRepository.getNotDeleted(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User get(String id) {
        return getOrThrow(id);
    }

    @Override
    public User save(CreateUserForm userRequest) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .createdOn(Instant.now())
                .deletedOn(null)
                .deletedById(null)
                .isBlocked(false)
                .role(Role.CUSTOMER)
                .build();
        return userManagementRepository.save(user);
    }

    @Override
    public User saveSysAdminOnStartup(CreateUserForm userRequest) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.ADMIN)
                .build();

        return userManagementRepository.save(user);
    }

    @Override
    public User update(EditUserForm userRequest) {
        User user = getOrThrow(userRequest.getId());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());

        return userManagementRepository.save(user);
    }

    @Override
    public User block(String id) {
        User user = getOrThrow(id);
        user.setBlocked(true);
        return userManagementRepository.save(user);
    }

    @Override
    public User unBlock(String id) {
        User user = getOrThrow(id);
        user.setBlocked(false);
        return userManagementRepository.save(user);
    }

    @Override
    public void delete(String id, String deletedById) {
        User user = getOrThrow(id);
        user.setDeletedOn(Instant.now());
        user.setDeletedById(deletedById);
        userManagementRepository.save(user);
    }
}
