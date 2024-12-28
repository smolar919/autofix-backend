package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.CreateEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.EditEmployeeForm;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.repository.EmployeeRepository;
import com.kamilsmolarek.autofix.workshop.repository.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final WorkshopRepository workshopRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, WorkshopRepository workshopRepository) {
        this.employeeRepository = employeeRepository;
        this.workshopRepository = workshopRepository;
    }

    @Override
    public SearchResponse<Employee> search(SearchForm form) {
        return employeeRepository.search(form);
    }

    private Employee getOrThrow(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public Employee create(CreateEmployeeForm form) {
        Workshop workshop = workshopRepository.findById(form.getWorkshopId()).orElseThrow(() -> new ApplicationException(ErrorCode.WORKSHOP_NOT_FOUND));

        Employee employee = Employee.builder()
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .phoneNumber(form.getPhoneNumber())
                .position(form.getPosition())
                .lastName(form.getLastName())
                .id(UUID.randomUUID().toString())
                .workshopId(form.getWorkshopId())
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee edit(String id, EditEmployeeForm form) {
        Employee employee = getOrThrow(id);
        employee.setEmail(form.getEmail());
        employee.setPosition(form.getPosition());
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setPhoneNumber(form.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        getOrThrow(id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> listByWorkshop(String workshopId) {
        return employeeRepository.findByWorkshopId(workshopId);
    }
}
