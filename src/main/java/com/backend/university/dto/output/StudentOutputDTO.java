package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentOutputDTO {

    private Long id;

    private String cpf;

    private String name;

    private String email;

    private LocalDate birthDate;

}