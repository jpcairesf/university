package com.backend.university.department.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.DepartmentUpdateDTO;
import com.backend.university.department.dto.mapper.DepartmentMapper;
import com.backend.university.department.repository.DepartmentRepository;
import com.backend.university.institute.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class DepartmentUpdateAction {

    private final DepartmentRepository repository;

    private final InstituteService instituteService;

    private final DepartmentValidatorAction validatorAction;

    @Transactional
    public DepartmentOutputDTO update(DepartmentUpdateDTO update) {
        Department department = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(department.getName())) {
            this.validatorAction.validateExistsByName(update.getName());
            department.setName(update.getName());
        }
        if (!update.getInstitute().equalsIgnoreCase(department.getInstitute().getName())) {
            department.setInstitute(instituteService.findEntityByName(update.getInstitute()));
        }
        repository.save(department);
        return DepartmentMapper.entityToOutput(department);
    }

    private Department findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no department with ID \"%s\".", id)));
    }

}
