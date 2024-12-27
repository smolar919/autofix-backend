package com.kamilsmolarek.autofix.user.management.repository;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.user.management.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserManagementRepository {

    SearchResponse<User> search(SearchForm form);
    Optional<User> get(String id);
    Optional<User> getNotDeleted(String id);
    User save(User user);
}

