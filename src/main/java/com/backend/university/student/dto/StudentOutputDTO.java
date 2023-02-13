package com.backend.university.student.dto;

import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class StudentOutputDTO {

    private Long id;

    private String courseName;

    private int enrollmentNumber;

    private LocalDate enrollmentDate;

    private String cpf;

    private String name;

    private String email;

    private List<StudentSubjectOutputDTO> enrollmentSubjects;

}
