package com.backend.university.subject.dto;

import com.backend.university.subject.domain.enumx.SubjectSchedule;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class SubjectOutputDTO {

    private Long id;

    private String code;

    private String name;

    private String professorCpf;

    private String room;

    private Set<EnrollmentSubjectOutputDTO> enrollmentSubjects;

    private List<Pair<DayOfWeek, SubjectSchedule>> schedule;

    private int studyLoad;

    private int vacancies;

}
