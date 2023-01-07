package com.backend.university.dto.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfessorInputDTO {

    private String name;

    private String cpf;

    private String email;

    private String birthDate;

    private String hiringDate;

    private String department;

    private String rank;

    private String degree;

}
