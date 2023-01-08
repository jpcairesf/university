package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartmentUpdateDTO {

    private Long id;

    private String name;

    private String institute;

    private List<ProfessorUpdateDTO> professors;

    public void addProfessor(ProfessorUpdateDTO professor) {
        professor.setDepartment(this.name);
        this.professors.add(professor);
    }

    public void removeProfessor(ProfessorUpdateDTO professorUpdateDTO) {
        this.professors.remove(professorUpdateDTO);
    }

    public void removeProfessor(String cpf) {
        this.professors.removeIf(p -> p.getCpf().equalsIgnoreCase(cpf));
    }

}
