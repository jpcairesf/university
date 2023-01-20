package com.backend.university.department.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentUpdateDTO {

    private Long id;

    private String name;

    private String institute;

}
