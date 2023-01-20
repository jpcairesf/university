package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DepartmentOutputDTO {

    private Long id;

    private String name;

    private String institute;

    private Set<ProfessorOutputDTO> professors;

}
