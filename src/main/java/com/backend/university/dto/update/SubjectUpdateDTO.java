package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectUpdateDTO {

    private Long id;

    private String code;

    private String name;

    private String professorCpf;

    private String room;

    private int studyLoad;

    private int vacancies;

}
