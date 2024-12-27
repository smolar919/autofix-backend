package com.kamilsmolarek.autofix.user.management.service;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.forms.CreateUserForm;
import com.kamilsmolarek.autofix.user.management.forms.EditUserForm;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserManagementService {

    SearchResponse<User> search(SearchForm form);
    User get(String id);
    User save(@Valid CreateUserForm userRequest, String createdById);
    User saveSysAdminOnStartup(@Valid CreateUserForm userRequest);
    User update(@Valid EditUserForm userRequest);

    User block(String id);
    User unBlock(String id);
    void delete(String id, String deletedById);

}
