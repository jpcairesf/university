package com.backend.university.employee.action;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.exception.EmployeeExceptionSupplier;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeDeleteAction {

    private final EmployeeRepository repository;

    public void delete(Long id) {
        repository.delete(findEntityById(id));
    }

    private Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(EmployeeExceptionSupplier.notFoundById(id));
    }

}
