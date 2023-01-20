package com.backend.university.department.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentInputDTO {

    private String name;

    private String institute;

}
