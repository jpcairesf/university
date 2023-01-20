package com.backend.university.dto.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSubjectInputDTO {

    private String course;

    private String subjectCode;

    private boolean required;

    private int semester;

}
