package com.backend.university.student.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentInputDTO {

    private String name;

    private String cpf;

    private String email;

    private int enrollmentNumber;

    private LocalDate birthDate;

}
