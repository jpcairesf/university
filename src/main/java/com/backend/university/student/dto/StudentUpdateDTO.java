package com.backend.university.student.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentUpdateDTO {

    private Long id;

    private int enrollmentNumber;

    private LocalDate enrollmentDate;

    private String cpf;

    private String name;

    private String email;

}
