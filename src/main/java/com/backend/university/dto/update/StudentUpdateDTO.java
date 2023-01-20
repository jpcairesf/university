package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentUpdateDTO {

    private Long id;

    private String cpf;

    private String name;

    private String email;

    private int enrollmentNumber;

    private LocalDate birthDate;

}
