package com.backend.university.department.dto;

import com.backend.university.professor.dto.ProfessorOutputDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartmentOutputDTO {

    private Long id;

    private String name;

    private String institute;

    private List<ProfessorOutputDTO> professors;

}
