package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.AddExistingEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.CreateNewEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.EditEmployeeForm;
import com.kamilsmolarek.autofix.workshop.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createNew(CreateNewEmployeeForm form);
    Employee addExisting(AddExistingEmployeeForm form);
    Employee edit(String id, EditEmployeeForm form);
    void delete(String id);
    List<Employee> listByWorkshop(String workshopId);
    SearchResponse<Employee> search(SearchForm form);
}