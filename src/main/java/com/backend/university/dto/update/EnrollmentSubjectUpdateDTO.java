package com.backend.university.dto.update;

import com.backend.university.domain.id.EnrollmentSubjectId;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EnrollmentSubjectUpdateDTO {

    private EnrollmentSubjectId id;

    private int enrollmentNumber;

    private String subjectCode;

    private int semester;

    private BigDecimal grade;

}
