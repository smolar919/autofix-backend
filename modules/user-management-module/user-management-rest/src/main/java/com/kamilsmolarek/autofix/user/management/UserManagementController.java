package com.kamilsmolarek.autofix.user.management;

import com.kamilsmolarek.autofix.commons.LoggedUser;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.user.management.forms.CreateUserForm;
import com.kamilsmolarek.autofix.user.management.forms.EditUserForm;
import com.kamilsmolarek.autofix.user.management.service.UserManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-management")
public class UserManagementController {

    private final UserManagementService userManagementService;
    private final LoggedUser loggedUser;

    public UserManagementController(UserManagementService userManagementService, LoggedUser loggedUser) {
        this.userManagementService = userManagementService;
        this.loggedUser = loggedUser;
    }

    @PostMapping("/search")
    public SearchResponse<User> search(@RequestBody SearchForm form) {
        return userManagementService.search(form);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String id) {
        return userManagementService.get(id);
    }

    @PostMapping("/save")
    public User save(@RequestBody CreateUserForm createUserForm) {
        return userManagementService.save(createUserForm);
    }

    @PutMapping("/update")
    public User update(@RequestBody EditUserForm editUserForm) {
        return userManagementService.update(editUserForm);
    }

    @PutMapping("/block/{id}")
    public User block(@PathVariable("id") String id) {
        return userManagementService.block(id);
    }

    @PutMapping("/unblock/{id}")
    public User unblock(@PathVariable("id") String id) {
        return userManagementService.unBlock(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        userManagementService.delete(id, loggedUser.getUserId());
    }
}
