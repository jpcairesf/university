package com.backend.university.subject.dto.mapper;

import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.enrollmentsubject.dto.mapper.EnrollmentSubjectMapper;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectMapper {

    public static SubjectOutputDTO entityToOutput(Subject subject) {
        List<EnrollmentSubjectOutputDTO> subjects =
                subject.getEnrollmentSubjects().stream()
                .map(EnrollmentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return SubjectOutputDTO.builder()
                .id(subject.getId())
                .code(subject.getCode())
                .name(subject.getName())
                .studyLoad(subject.getStudyLoad())
                .vacancies(subject.getVacancies())
                .schedule(subject.getSchedule())
                .room(subject.getRoom().getName())
                .professorCpf(subject.getProfessor().getCpf())
                .enrollmentSubjects(subjects)
                .build();
    }

}
