package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.workshop.forms.CreateEmployeeForm;
import com.kamilsmolarek.autofix.workshop.forms.EditEmployeeForm;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeRepositoryMock employeeRepositoryMock;
    private WorkshopRepositoryMock workshopRepositoryMock;

    @BeforeEach
    void setUp() {
        employeeRepositoryMock = new EmployeeRepositoryMock();
        workshopRepositoryMock = new WorkshopRepositoryMock();
        employeeService = new EmployeeServiceImpl(employeeRepositoryMock, workshopRepositoryMock);
    }

    @Test
    void testCreateEmployee() {
        Workshop workshop = workshopRepositoryMock.save(new Workshop("1", "Warsztat 1", null, null));
        CreateEmployeeForm form = new CreateEmployeeForm("Jan", "Nowak", "Mechanik", "123456789", "jan.nowak@example.com", workshop.getId());
        Employee employee = employeeService.create(form);

        assertNotNull(employee);
        assertEquals("Jan", employee.getFirstName());
        assertEquals(workshop.getId(), employee.getWorkshopId());
    }

    @Test
    void testEditEmployee() {
        Workshop workshop = workshopRepositoryMock.save(new Workshop("1", "Warsztat 1", null, null));
        Employee employee = employeeRepositoryMock.save(new Employee("1", "Jan", "Nowak", "Mechanik", "123456789", "jan.nowak@example.com","workshopId"));

        EditEmployeeForm form = new EditEmployeeForm("Jan", "Nowak", "Menadzer", "987654321", "jan.nowak@example.com");
        Employee updatedEmployee = employeeService.edit(employee.getId(), form);

        assertNotNull(updatedEmployee);
        assertEquals("Jan", updatedEmployee.getFirstName());
        assertEquals("Menadzer", updatedEmployee.getPosition());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = employeeRepositoryMock.save(new Employee("1", "Jan", "Nowak", "Mechanik", "123456789", "jan.nowak@example.com", null));

        employeeService.delete(employee.getId());

        assertFalse(employeeRepositoryMock.findById(employee.getId()).isPresent());
    }

    @Test
    void testListByWorkshop() {
        Workshop workshop = workshopRepositoryMock.save(new Workshop("1", "Warsztat 1", null, null));
        employeeRepositoryMock.save(new Employee("1", "Jan", "Nowak", "Mechanik", "123456789", "jan.nowak@example.com", "workshop"));
        employeeRepositoryMock.save(new Employee("2", "Piotr", "Kowalski", "Menadzer", "987654321", "piotr.kowalski@example.com", "workshop"));

        List<Employee> employees = employeeService.listByWorkshop(workshop.getId());

        assertEquals(2, employees.size());
    }
}
