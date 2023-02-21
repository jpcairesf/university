package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.exception.DepartmentExceptionSupplier;
import com.backend.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DepartmentDeleteAction {

    private final DepartmentRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Department findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(DepartmentExceptionSupplier.departmentNotFoundById(id));
    }

}
