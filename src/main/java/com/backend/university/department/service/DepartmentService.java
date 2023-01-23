package com.backend.university.department.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.DepartmentUpdateDTO;
import com.backend.university.department.dto.mapper.DepartmentMapper;
import com.backend.university.department.repository.DepartmentRepository;
import com.backend.university.institute.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {

    private final DepartmentRepository repository;

    private final DepartmentMapper mapper;

    private final InstituteService instituteService;

    @Transactional
    public DepartmentOutputDTO create(DepartmentInputDTO input) {
        this.validateExistsByName(input.getName());
        Department department = mapper.inputToEntity(input);
        repository.save(department);
        return mapper.entityToOutput(department);
    }

    @Transactional
    public DepartmentOutputDTO update(DepartmentUpdateDTO update) {
        Department department = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(department.getName())) {
            this.validateExistsByName(update.getName());
            department.setName(update.getName());
        }
        if (!update.getInstitute().equalsIgnoreCase(department.getInstitute().getName())) {
            department.setInstitute(instituteService.findEntityByName(update.getInstitute()));
        }
        repository.save(department);
        return mapper.entityToOutput(department);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Department findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no department named \"%s\".", name)));
    }

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a department named \"%s\".", name));
        }
    }

    public Department findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no department with ID \"%s\".", id)));
    }

}
