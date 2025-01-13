package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.user.management.Role;
import com.kamilsmolarek.autofix.user.management.User;
import com.kamilsmolarek.autofix.user.management.repository.UserManagementRepository;
import com.kamilsmolarek.autofix.workshop.forms.CreateWorkshopForm;
import com.kamilsmolarek.autofix.workshop.forms.EditWorkshopForm;
import com.kamilsmolarek.autofix.workshop.model.Address;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.repository.EmployeeRepository;
import com.kamilsmolarek.autofix.workshop.repository.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkshopServiceImpl implements WorkshopService {
    private final WorkshopRepository workshopRepository;
    private final UserManagementRepository userManagementRepository;
    private final EmployeeRepository employeeRepository;

    public WorkshopServiceImpl(WorkshopRepository workshopRepository, UserManagementRepository userManagementRepository, EmployeeRepository employeeRepository) {
        this.workshopRepository = workshopRepository;
        this.userManagementRepository = userManagementRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Workshop create(CreateWorkshopForm form) {
        Address address = Address.builder()
                .id(UUID.randomUUID().toString())
                .city(form.getCity())
                .country(form.getCountry())
                .voivodeship(form.getVoivodeship())
                .postalCode(form.getPostalCode())
                .street(form.getStreet())
                .build();

        Workshop workshop = Workshop.builder()
                .name(form.getName())
                .address(address)
                .id(UUID.randomUUID().toString())
                .employees(new ArrayList<>())
                .ownerId(form.getOwnerId())
                .isVisible(true)// dopiero po platnosciach zostanie zmienione
                .build();

        // Zapis warsztatu
        workshopRepository.save(workshop);

        // Znalezienie właściciela
        User owner = userManagementRepository.get(form.getOwnerId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        // Zmiana roli na WORKSHOP_OWNER
        owner.setRole(Role.PROVIDER);
        userManagementRepository.save(owner);

        // Dodanie właściciela jako pracownika warsztatu
        Employee ownerEmployee = Employee.builder()
                .id(UUID.randomUUID().toString())
                .userId(owner.getId())
                .email(owner.getEmail())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .position("Właściciel")
                .phoneNumber(form.getOwnerPhoneNumber())
                .workshopId(workshop.getId())
                .build();

        employeeRepository.save(ownerEmployee);

        return workshop;
    }

    @Override
    public Workshop edit(String id, EditWorkshopForm form) {
        Workshop workshop = getOrThrow(id);

        Address address = workshop.getAddress();
        address.setCity(form.getCity());
        address.setCountry(form.getCountry());
        address.setStreet(form.getStreet());
        address.setVoivodeship(form.getVoivodeship());
        address.setPostalCode(form.getPostalCode());

        workshop.setName(form.getName());
        workshop.setAddress(address);

        return workshopRepository.save(workshop);
    }

    @Override
    public void delete(String id) {
        Workshop workshop = getOrThrow(id);

        // Znalezienie właściciela warsztatu
        User owner = userManagementRepository.get(workshop.getOwnerId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        // Zmiana roli właściciela na CUSTOMER
        owner.setRole(Role.CUSTOMER);
        userManagementRepository.save(owner);

        // Usunięcie pracowników warsztatu
        List<Employee> employees = employeeRepository.findByWorkshopId(workshop.getId());
        for (Employee employee : employees) {
            User employeeUser = userManagementRepository.get(employee.getUserId())
                    .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
            employeeUser.setRole(Role.CUSTOMER);
            userManagementRepository.save(employeeUser);
            employeeRepository.deleteById(employee.getId());
        }

        // Usunięcie warsztatu
        workshopRepository.deleteById(workshop.getId());
    }

    @Override
    public SearchResponse<Workshop> search(SearchForm form) {
        return workshopRepository.search(form);
    }

    @Override
    public Workshop get(String id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.WORKSHOP_NOT_FOUND));
    }

    private Workshop getOrThrow(String id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.WORKSHOP_NOT_FOUND));
    }
}
