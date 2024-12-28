package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.repository.EmployeeRepository;

import java.util.*;

public class EmployeeRepositoryMock implements EmployeeRepository {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee save(Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public List<Employee> findByWorkshopId(String workshopId) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getWorkshop().getId().equals(workshopId)) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public void deleteById(String id) {
        employees.remove(id);
    }

    @Override
    public SearchResponse<Employee> search(SearchForm form) {
        return new SearchResponse<>(new ArrayList<>(employees.values()), (long) employees.size());
    }
}
