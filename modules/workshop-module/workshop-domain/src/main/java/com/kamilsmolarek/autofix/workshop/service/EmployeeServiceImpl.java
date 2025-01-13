package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.login.pass.auth.AuthPassUser;
import com.kamilsmolarek.autofix.login.pass.auth.repository.AuthorizationRepository;
import com.kamilsmolarek.autofix.user.management.Role;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.repository.UserManagementRepository;
import com.kamilsmolarek.autofix.workshop.forms.AddExistingEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.CreateNewEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.EditEmployeeForm;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.repository.EmployeeRepository;
import com.kamilsmolarek.autofix.workshop.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import com.kamilsmolarek.autofix.login.pass.auth.PasswordEncoder;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final WorkshopRepository workshopRepository;
    private final UserManagementRepository userManagementRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationRepository authorizationRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, WorkshopRepository workshopRepository,
                               UserManagementRepository userManagementRepository, PasswordEncoder passwordEncoder,
                               AuthorizationRepository authorizationRepository) {
        this.employeeRepository = employeeRepository;
        this.workshopRepository = workshopRepository;
        this.userManagementRepository = userManagementRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Employee createNew(CreateNewEmployeeForm form) {
        // Znalezienie warsztatu
        Workshop workshop = workshopRepository.findById(form.getWorkshopId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.WORKSHOP_NOT_FOUND));

        // Generowanie hasła, jeśli nie podano
        String generatedPassword = form.getPassword() != null ? form.getPassword() : generateRandomPassword();

        // Tworzenie nowego użytkownika
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.PROVIDER)
                .build();

        userManagementRepository.save(user);

        AuthPassUser authPassUser = AuthPassUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(passwordEncoder.encode(generatedPassword))
                .build();

        authorizationRepository.saveAuthPassUser(authPassUser);

        // Wysłanie hasła do użytkownika (implementacja zależna od potrzeb)
        sendPasswordToUserEmail(user.getEmail(), generatedPassword);

        // Tworzenie pracownika
        return saveEmployee(user, form.getPosition(), form.getPhoneNumber(), form.getWorkshopId());
    }

    @Override
    public Employee addExisting(AddExistingEmployeeForm form) {
        // Znalezienie warsztatu
        Workshop workshop = workshopRepository.findById(form.getWorkshopId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.WORKSHOP_NOT_FOUND));

        // Znalezienie istniejącego użytkownika po emailu
        User user = userManagementRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        // Sprawdzenie, czy użytkownik nie jest już pracownikiem innego warsztatu
        if (user.getRole() == Role.PROVIDER && isEmployeeOfAnotherWorkshop(user.getId(), workshop.getId())) {
            throw new ApplicationException(ErrorCode.USER_ALREADY_EMPLOYEE_OF_ANOTHER_WORKSHOP);
        }

        // Sprawdzenie, czy użytkownik jest zablokowany
        if (user.isBlocked()) {
            throw new ApplicationException(ErrorCode.USER_IS_BLOCKED);
        }

        // Przypisanie roli EMPLOYEE
        user.setRole(Role.PROVIDER);
        userManagementRepository.save(user);

        // Tworzenie pracownika
        return saveEmployee(user, form.getPosition(), form.getPhoneNumber(), form.getWorkshopId());
    }

    private Employee saveEmployee(User user, String position, String phoneNumber, String workshopId) {
        Employee employee = Employee.builder()
                .id(UUID.randomUUID().toString())
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .position(position)
                .phoneNumber(phoneNumber)
                .workshopId(workshopId)
                .build();

        return employeeRepository.save(employee);
    }

    private boolean isEmployeeOfAnotherWorkshop(String userId, String workshopId) {
        List<Employee> employees = employeeRepository.findByUserId(userId);
        return employees.stream()
                .anyMatch(employee -> !employee.getWorkshopId().equals(workshopId));
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 12);
    }

    private void sendPasswordToUserEmail(String email, String password) {
        // Implementacja wysyłki e-maila z hasłem
        // Możesz użyć np. Spring Email lub innego rozwiązania do wysyłania wiadomości e-mail
        System.out.printf("Wysłano e-mail z hasłem do użytkownika: %s. Hasło: %s%n", email, password);
    }

    @Override
    public Employee edit(String id, EditEmployeeForm form) {
        Employee employee = getOrThrow(id);
        employee.setEmail(form.getEmail());
        employee.setPosition(form.getPosition());
        employee.setPhoneNumber(form.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        Employee employee = getOrThrow(id);
        User user = userManagementRepository.get(employee.getUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        employeeRepository.deleteById(id);
        user.setRole(Role.CUSTOMER);
        userManagementRepository.save(user);
    }

    @Override
    public List<Employee> listByWorkshop(String workshopId) {
        return employeeRepository.findByWorkshopId(workshopId);
    }

    @Override
    public SearchResponse<Employee> search(SearchForm form) {
        return employeeRepository.search(form);
    }

    private Employee getOrThrow(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }
}
