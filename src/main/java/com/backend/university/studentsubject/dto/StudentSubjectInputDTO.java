package com.backend.university.studentsubject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentSubjectInputDTO {

    private int enrollmentNumber;

    private String subjectCode;

    private int semester;

    private int classNumber;

}
