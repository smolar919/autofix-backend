package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.CreateEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.EditEmployeeForm;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/search")
    public SearchResponse<Employee> search(@RequestBody SearchForm form) {
        return employeeService.search(form);
    }

    @PostMapping
    public Employee create(@RequestBody CreateEmployeeForm form) {
        return employeeService.create(form);
    }

    @PutMapping("/{id}")
    public Employee edit(@PathVariable String id, @RequestBody EditEmployeeForm form) {
        return employeeService.edit(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        employeeService.delete(id);
    }

    @GetMapping("/workshop/{workshopId}")
    public List<Employee> listByWorkshop(@PathVariable String workshopId) {
        return employeeService.listByWorkshop(workshopId);
    }
}
