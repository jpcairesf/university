package com.backend.university.subject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectUpdateDTO {

    private Long id;

    private String code;

    private String name;

    private int studyLoad;

}
