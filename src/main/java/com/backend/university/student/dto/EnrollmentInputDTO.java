package com.backend.university.student.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnrollmentInputDTO {

    private String studentCpf;

    private String course;

    private int number;

    private LocalDate enrollmentDate;

}
