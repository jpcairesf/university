package com.backend.university.dto.mapper;

import com.backend.university.domain.Department;
import com.backend.university.domain.Institute;
import com.backend.university.dto.input.DepartmentInputDTO;
import com.backend.university.dto.output.DepartmentOutputDTO;
import com.backend.university.dto.output.ProfessorOutputDTO;
import com.backend.university.dto.update.DepartmentUpdateDTO;
import com.backend.university.service.DepartmentService;
import com.backend.university.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentMapper {

    private final ProfessorMapper professorMapper;

    private final DepartmentService departmentService;

    private final InstituteService instituteService;

    public Department inputToEntity(DepartmentInputDTO input) {
        Department department = new Department();
        department.setName(input.getName());
        department.setInstitute(instituteService.findEntityByName(input.getInstitute()));
        department.setProfessors(new HashSet<>());
        return department;
    }

    public Department updateToEntity(DepartmentUpdateDTO update) {
        Department department = departmentService.findEntityById(update.getId());
        if (!update.getInstitute().equalsIgnoreCase(department.getInstitute().getName())) {
            department.setInstitute(instituteService.findEntityByName(update.getInstitute()));
        }
        setIfNotNull(update.getName(), department::setName);
        return department;
    }

    public DepartmentOutputDTO entityToOutput(Department department) {
        Set<ProfessorOutputDTO> professors =
                department.getProfessors().stream()
                .map(professorMapper::entityToOutput)
                .collect(Collectors.toSet());

        return DepartmentOutputDTO.builder()
                .name(department.getName())
                .institute(department.getInstitute().getName())
                .professors(professors)
                .build();
    }

}
