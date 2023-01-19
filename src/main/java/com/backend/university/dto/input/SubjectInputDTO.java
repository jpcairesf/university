package com.backend.university.dto.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectInputDTO {

    private String name;

    private String code;

    private String professorCpf;

    private String room;

    private int studyLoad;

    private int vacancies;

}
