package com.backend.university.studentsubject.dto;

import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StudentSubjectOutputDTO {

    private StudentSubjectId id;

    private int enrollmentNumber;

    private String subjectCode;

    private int semester;

    private int classNumber;

    private BigDecimal grade;

}
