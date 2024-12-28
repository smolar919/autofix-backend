package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.commons.SearchSpecification;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.entity.EmployeeEntity;
import com.kamilsmolarek.autofix.workshop.entity.WorkshopEntity;
import com.kamilsmolarek.autofix.workshop.mapper.EmployeeMapper;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepository{
    private final EmployeeRepositoryJpa repositoryJpa;
    private final WorkshopRepositoryJpa workshopRepositoryJpa;

    public EmployeeRepositoryAdapter(EmployeeRepositoryJpa repositoryJpa, WorkshopRepositoryJpa workshopRepositoryJpa) {
        this.repositoryJpa = repositoryJpa;
        this.workshopRepositoryJpa = workshopRepositoryJpa;
    }

    @Override
    public Employee save(Employee employee) {
        WorkshopEntity workshopEntity = workshopRepositoryJpa.getById(employee.getWorkshopId());
        EmployeeEntity employeeEntity = EmployeeMapper.toEntity(employee);
        employeeEntity.setWorkshop(workshopEntity);
        repositoryJpa.save(employeeEntity);
        return EmployeeMapper.toEmployee(employeeEntity);
    }

    @Override
    public Optional<Employee> findById(String id) {
        Optional<EmployeeEntity> employeeEntity = repositoryJpa.findById(id);
        return employeeEntity.map(EmployeeMapper::toEmployee);
    }

    @Override
    public List<Employee> findByWorkshopId(String workshopId) {
        return repositoryJpa.findByWorkshopId(workshopId).stream()
                .map(EmployeeMapper::toEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public SearchResponse<Employee> search(SearchForm form) {
        Specification<EmployeeEntity> specification = SearchSpecification.buildSpecification(form.getCriteria());
        Page<EmployeeEntity> userPage = repositoryJpa.findAll(specification, SearchSpecification.getPageRequest(form));
        return SearchResponse.<Employee>builder()
                .items(userPage.getContent().stream()
                        .map(EmployeeMapper::toEmployee)
                        .collect(Collectors.toList()))
                .total(userPage.getTotalElements())
                .build();
    }
}
