package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.mapper.DepartmentMapper;
import com.backend.university.department.repository.DepartmentRepository;
import com.backend.university.institute.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DepartmentCreateAction {

    private final DepartmentRepository repository;

    private final InstituteService instituteService;

    private final DepartmentValidatorAction validatorAction;

    @Transactional
    public DepartmentOutputDTO create(DepartmentInputDTO input) {
        validatorAction.validateExistsByName(input.getName());

        Department department = new Department();
        department.setName(input.getName());
        department.setInstitute(instituteService.findEntityByName(input.getInstitute()));

        repository.save(department);
        return DepartmentMapper.entityToOutput(department);
    }

}
