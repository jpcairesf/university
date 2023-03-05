package com.backend.university.employee.action;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.exception.EmployeeExceptionSupplier;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeGetAction {

    private final EmployeeRepository repository;

    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(EmployeeExceptionSupplier.notFoundById(id));
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

}