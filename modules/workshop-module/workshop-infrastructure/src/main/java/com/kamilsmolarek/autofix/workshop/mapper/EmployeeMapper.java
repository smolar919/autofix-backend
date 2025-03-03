package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.EmployeeEntity;
import com.kamilsmolarek.autofix.workshop.model.Employee;

public class EmployeeMapper {

    public static Employee toEmployee(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }

        return Employee.builder()
                .id(employeeEntity.getId())
                .userId(employeeEntity.getUserId())
                .email(employeeEntity.getEmail())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .position(employeeEntity.getPosition())
                .workshopId(employeeEntity.getWorkshop().getId())
                .build();
    }

    public static EmployeeEntity toEntity(Employee employee) {
        if (employee == null) {
            return null;
        }

        return EmployeeEntity.builder()
                .id(employee.getId())
                .userId(employee.getUserId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .position(employee.getPosition())
                .build();
    }
}

