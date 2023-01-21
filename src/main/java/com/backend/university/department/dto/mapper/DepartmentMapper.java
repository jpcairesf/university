package com.backend.university.department.dto.mapper;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.institute.service.InstituteService;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentMapper {

    private final ProfessorMapper professorMapper;

    private final InstituteService instituteService;

    public Department inputToEntity(DepartmentInputDTO input) {
        Department department = new Department();
        department.setName(input.getName());
        department.setInstitute(instituteService.findEntityByName(input.getInstitute()));
        department.setProfessors(new HashSet<>());
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
