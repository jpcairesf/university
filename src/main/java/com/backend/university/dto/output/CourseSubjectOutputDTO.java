package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSubjectOutputDTO {

    private Long id;

    private String course;

    private String subjectCode;

    private boolean required;

    private int semester;

}
