package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.AddExistingEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.CreateNewEmployeeForm;
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

    @PostMapping("/create-new")
    public Employee createNew(@RequestBody CreateNewEmployeeForm form) {
        return employeeService.createNew(form);
    }

    @PostMapping("/add-existing")
    public Employee addExisting(@RequestBody AddExistingEmployeeForm form) {
        return employeeService.addExisting(form);
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

    @PostMapping("/search")
    public SearchResponse<Employee> search(@RequestBody SearchForm form) {
        return employeeService.search(form);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id) {
        return employeeService.getEmployee(id);
    }
}