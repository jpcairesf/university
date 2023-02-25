package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.exception.DepartmentExceptionSupplier;
import com.backend.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentRelatedAction {

    private final DepartmentRepository repository;

    public Department findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(DepartmentExceptionSupplier.notFoundByName(name));
    }

}
