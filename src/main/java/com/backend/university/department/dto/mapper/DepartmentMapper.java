package com.backend.university.department.dto.mapper;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DepartmentMapper {

    public static DepartmentOutputDTO entityToOutput(Department department) {

        return DepartmentOutputDTO.builder()
                .name(department.getName())
                .institute(department.getInstitute().getName())
                .build();
    }

}
