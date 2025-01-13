package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(String id);
    List<Employee> findByWorkshopId(String workshopId);
    void deleteById(String id);
    SearchResponse<Employee> search(SearchForm form);
    List<Employee> findByUserId(String userId);
}
