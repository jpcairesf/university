package com.backend.university.coursesubject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSubjectInputDTO {

    private String course;

    private String subjectCode;

    private boolean required;

    private int curriculumYear;

}
