package com.backend.university.department.dto.mapper;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentOutputDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DepartmentMapper {

    public static DepartmentOutputDTO entityToOutput(Department department) {

        return DepartmentOutputDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .institute(department.getInstitute().getName())
                .build();
    }

}
