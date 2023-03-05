package com.backend.university.coursesubject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSubjectOutputDTO {

    private Long id;

    private String courseName;

    private String subjectCode;

    private boolean required;

    private int curriculumYear;

}
