package com.backend.university.subject.dto;

import com.backend.university.studentsubject.dto.EnrollmentSubjectOutputDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubjectOutputDTO {

    private Long id;

    private String code;

    private String name;

    private String professorCpf;

    private String room;

    private List<EnrollmentSubjectOutputDTO> enrollmentSubjects;

    private String schedule;

    private int studyLoad;

    private int vacancies;

}
