package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentUpdateDTO;
import com.backend.university.department.exception.DepartmentExceptionSupplier;
import com.backend.university.department.repository.DepartmentRepository;
import com.backend.university.institute.action.InstituteRelatedAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentUpdateAction {

    private final DepartmentRepository repository;

    private final InstituteRelatedAction instituteRelatedAction;

    private final DepartmentValidatorAction validatorAction;

    public Department update(DepartmentUpdateDTO update) {
        Department department = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(department.getName())) {
            validatorAction.validateExistsByName(update.getName());
            department.setName(update.getName());
        }
        if (!update.getInstitute().equalsIgnoreCase(department.getInstitute().getName())) {
            department.setInstitute(instituteRelatedAction.findEntityByName(update.getInstitute()));
        }

        return repository.save(department);
    }

    private Department findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(DepartmentExceptionSupplier.notFoundById(id));
    }

}
