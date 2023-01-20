package com.backend.university.employee.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeOutputDTO {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private LocalDate birthDate;

    private LocalDate hiringDate;

}
