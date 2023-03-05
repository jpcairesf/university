package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.exception.DepartmentExceptionSupplier;
import com.backend.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentGetAction {

    private final DepartmentRepository repository;

    public Department findById(Long id) {
        return repository.findById(id)
                .orElseThrow(DepartmentExceptionSupplier.notFoundById(id));
    }

    public List<Department> findAll() {
        return repository.findAll();
    }

}
