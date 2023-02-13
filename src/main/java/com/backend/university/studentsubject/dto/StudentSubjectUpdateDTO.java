package com.backend.university.studentsubject.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StudentSubjectUpdateDTO {

    private int enrollmentNumber;

    private String subjectCode;

    private int semester;

    private int classNumber;

    private BigDecimal grade;

}
