package com.backend.university.dto.input;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SubjectInputDTO {

    private String name;

    private String code;

    private String professorCpf;

    private String room;

    private Set<String> schedule;

    private int studyLoad;

    private int vacancies;

}
