package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EnrollmentOutputDTO {

    private Long id;

    private String studentCpf;

    private String course;

    private List<EnrollmentSubjectOutputDTO> enrollmentSubjects;

    private int number;

    private LocalDate enrollmentDate;

}
