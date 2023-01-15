package com.backend.university.dto.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentSubjectInputDTO {

    private int enrollmentNumber;

    private String subjectCode;

    private int semester;

}