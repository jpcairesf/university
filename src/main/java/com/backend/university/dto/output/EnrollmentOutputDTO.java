package com.backend.university.dto.output;

import com.backend.university.dto.update.EnrollmentSubjectUpdateDTO;
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

    private List<EnrollmentSubjectUpdateDTO> enrollmentSubjects;

    private int number;

    private LocalDate enrollmentDate;

}
