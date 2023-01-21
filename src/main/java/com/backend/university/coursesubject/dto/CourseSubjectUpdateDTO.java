package com.backend.university.coursesubject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSubjectUpdateDTO {

    private Long id;

    private String course;

    private String subjectCode;

    private Boolean required;

    private Integer semester;

}