package com.backend.university.dto.input;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentInputDTO {

    private String name;

    private String cpf;

    private String email;

    private LocalDate birthDate;

}