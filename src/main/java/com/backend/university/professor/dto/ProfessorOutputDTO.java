package com.backend.university.professor.dto;

import com.backend.university.professor.domain.enumx.Degree;
import com.backend.university.professor.domain.enumx.Rank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProfessorOutputDTO {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private LocalDate birthDate;

    private LocalDate hiringDate;

    private String department;

    private Rank rank;

    private Degree degree;

}
