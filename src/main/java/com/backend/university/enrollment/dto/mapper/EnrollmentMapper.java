package com.backend.university.enrollment.dto.mapper;

import com.backend.university.enrollment.domain.Enrollment;
import com.backend.university.enrollment.dto.EnrollmentOutputDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.enrollmentsubject.dto.mapper.EnrollmentSubjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentMapper {

    public static EnrollmentOutputDTO entityToOutput(Enrollment enrollment) {
        List<EnrollmentSubjectOutputDTO> subjects =
                enrollment.getEnrollmentSubjects().stream()
                .map(EnrollmentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return EnrollmentOutputDTO.builder()
                .id(enrollment.getId())
                .studentCpf(enrollment.getStudent().getCpf())
                .course(enrollment.getCourse().getName())
                .enrollmentSubjects(subjects)
                .number(enrollment.getNumber())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
    }

}
