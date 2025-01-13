package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.workshop.forms.CreateNewEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.EditEmployeeForm;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.user.management.Role;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.workshop.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeRepositoryMock employeeRepositoryMock;
    private UserManagementRepositoryMock userManagementRepositoryMock;
    private AuthorizationRepositoryMock authorizationRepositoryMock;
    private PasswordEncoderMock passwordEncoderMock;
    private WorkshopRepositoryMock workshopRepositoryMock;

    @BeforeEach
    void setUp() {
        employeeRepositoryMock = new EmployeeRepositoryMock();
        userManagementRepositoryMock = new UserManagementRepositoryMock();
        authorizationRepositoryMock = new AuthorizationRepositoryMock();
        passwordEncoderMock = new PasswordEncoderMock();
        workshopRepositoryMock = new WorkshopRepositoryMock();

        employeeService = new EmployeeServiceImpl(
                employeeRepositoryMock,
                workshopRepositoryMock,
                userManagementRepositoryMock,
                passwordEncoderMock,
                authorizationRepositoryMock
                );
    }

    @Test
    @DisplayName("Usuwanie pracownika powinno przejść pomyślnie")
    void testDeleteEmployee_Success() {
        User user = User.builder()
                .id("user-3")
                .firstName("Piotr")
                .lastName("Zieliński")
                .email("piotr.zielinski@example.com")
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.EMPLOYEE)
                .build();
        userManagementRepositoryMock.save(user);

        Employee employee = employeeRepositoryMock.save(new Employee(
                "emp-2",
                "user-3",
                "piotr.zielinski@example.com",
                "Piotr",
                "Zieliński",
                "Inżynier",
                "111222333",
                "workshop-4"
        ));

        employeeService.delete(employee.getId());

        Optional<Employee> deletedEmployeeOpt = employeeRepositoryMock.findById(employee.getId());
        assertFalse(deletedEmployeeOpt.isPresent(), "Pracownik powinien być usunięty z repozytorium");

        Optional<User> userOpt = userManagementRepositoryMock.get("user-3");
        assertTrue(userOpt.isPresent(), "Użytkownik powinien istnieć w repozytorium");
        User updatedUser = userOpt.get();
        assertEquals(Role.CUSTOMER, updatedUser.getRole(), "Rola użytkownika powinna być CUSTOMER");
    }
}
