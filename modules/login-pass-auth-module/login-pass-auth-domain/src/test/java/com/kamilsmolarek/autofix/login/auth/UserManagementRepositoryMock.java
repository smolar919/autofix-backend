package com.kamilsmolarek.autofix.login.auth;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.repository.UserManagementRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserManagementRepositoryMock implements UserManagementRepository {
    Map<String, User> mockDB = new HashMap<>();

    @Override
    public SearchResponse<User> search(SearchForm form) {
        return null;
    }

    @Override
    public Optional<User> get(String id) {
        return Optional.ofNullable(mockDB.get(id));
    }

    @Override
    public Optional<User> getNotDeleted(String id) {
        Optional<User> user= Optional.ofNullable(mockDB.get(id));
        if(user.isPresent() && user.get().getDeletedOn() == null) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        mockDB.put(user.getId(), user);
        return user;
    }
}
