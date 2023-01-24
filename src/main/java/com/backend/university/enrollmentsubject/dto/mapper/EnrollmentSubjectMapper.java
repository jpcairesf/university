package com.backend.university.enrollmentsubject.dto.mapper;

import com.backend.university.enrollmentsubject.domain.EnrollmentSubject;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentSubjectMapper {

    public static EnrollmentSubjectOutputDTO entityToOutput(EnrollmentSubject enrollmentSubject) {
        return EnrollmentSubjectOutputDTO.builder()
                .id(enrollmentSubject.getId())
                .enrollmentNumber(enrollmentSubject.getEnrollment().getNumber())
                .subjectCode(enrollmentSubject.getSubject().getCode())
                .semester(enrollmentSubject.getSemester())
                .grade(enrollmentSubject.getGrade())
                .build();
    }

}
