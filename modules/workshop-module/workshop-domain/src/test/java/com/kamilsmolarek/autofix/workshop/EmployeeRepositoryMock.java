package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.repository.EmployeeRepository;

import java.util.*;

public class EmployeeRepositoryMock implements EmployeeRepository {
    private final Map<String, Employee> employeesById = new HashMap<>();
    private final Map<String, List<Employee>> employeesByUserId = new HashMap<>();
    private final Map<String, List<Employee>> employeesByWorkshopId = new HashMap<>();

    @Override
    public Employee save(Employee employee) {
        employeesById.put(employee.getId(), employee);

        // Aktualizacja mapowania UserId
        employeesByUserId.computeIfAbsent(employee.getUserId(), k -> new ArrayList<>()).add(employee);

        // Aktualizacja mapowania WorkshopId
        if (employee.getWorkshopId() != null) {
            employeesByWorkshopId.computeIfAbsent(employee.getWorkshopId(), k -> new ArrayList<>()).add(employee);
        }

        return employee;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(employeesById.get(id));
    }

    @Override
    public List<Employee> findByWorkshopId(String workshopId) {
        return employeesByWorkshopId.getOrDefault(workshopId, Collections.emptyList());
    }

    @Override
    public void deleteById(String id) {
        Employee removed = employeesById.remove(id);
        if (removed != null) {
            // Usuwanie z mapowania UserId
            List<Employee> userEmployees = employeesByUserId.get(removed.getUserId());
            if (userEmployees != null) {
                userEmployees.removeIf(emp -> emp.getId().equals(id));
                if (userEmployees.isEmpty()) {
                    employeesByUserId.remove(removed.getUserId());
                }
            }
            // Usuwanie z mapowania WorkshopId
            if (removed.getWorkshopId() != null) {
                List<Employee> workshopEmployees = employeesByWorkshopId.get(removed.getWorkshopId());
                if (workshopEmployees != null) {
                    workshopEmployees.removeIf(emp -> emp.getId().equals(id));
                    if (workshopEmployees.isEmpty()) {
                        employeesByWorkshopId.remove(removed.getWorkshopId());
                    }
                }
            }
        }
    }

    @Override
    public SearchResponse<Employee> search(SearchForm form) {

        return new SearchResponse<>(new ArrayList<>(employeesById.values()), (long) employeesById.size());
    }

    @Override
    public List<Employee> findByUserId(String userId) {
        return employeesByUserId.getOrDefault(userId, Collections.emptyList());
    }
}
