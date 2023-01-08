package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfessorUpdateDTO {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private String birthDate;

    private String hiringDate;

    private String department;

    private String rank;

    private String degree;

}
