package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.repository.DepartmentRepository;
import com.backend.university.institute.action.InstituteRelatedAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentCreateAction {

    private final DepartmentRepository repository;

    private final InstituteRelatedAction instituteRelatedAction;

    private final DepartmentValidatorAction validatorAction;

    public Department create(DepartmentInputDTO input) {
        validatorAction.validateExistsByName(input.getName());

        Department department = new Department();
        department.setName(input.getName());
        department.setInstitute(instituteRelatedAction.findEntityByName(input.getInstitute()));

        return repository.save(department);
    }

}
