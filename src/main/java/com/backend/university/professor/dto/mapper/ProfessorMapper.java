package com.backend.university.professor.dto.mapper;

import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfessorMapper {

    public static ProfessorOutputDTO entityToOutput(Professor professor) {
        return ProfessorOutputDTO.builder()
                .id(professor.getId())
                .cpf(professor.getCpf())
                .name(professor.getName())
                .email(professor.getEmail())
                .birthDate(professor.getBirthDate())
                .hiringDate(professor.getHiringDate())
                .department(professor.getDepartment().getName())
                .rank(professor.getRank().getDescription())
                .degree(professor.getDegree().getDescription())
                .build();
    }

}
