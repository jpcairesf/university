package com.backend.university.enrollmentsubject.dto;

import com.backend.university.enrollmentsubject.domain.id.EnrollmentSubjectId;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EnrollmentSubjectOutputDTO {

    private EnrollmentSubjectId id;

    private int enrollmentNumber;

    private String subjectCode;

    private int semester;

    private BigDecimal grade;

}
