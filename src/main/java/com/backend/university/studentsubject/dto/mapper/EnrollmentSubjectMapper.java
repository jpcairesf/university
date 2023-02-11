package com.backend.university.studentsubject.dto.mapper;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.dto.EnrollmentSubjectOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentSubjectMapper {

    public static EnrollmentSubjectOutputDTO entityToOutput(StudentSubject studentSubject) {
        return EnrollmentSubjectOutputDTO.builder()
                .id(studentSubject.getId())
                .enrollmentNumber(studentSubject.getEnrollment().getNumber())
                .subjectCode(studentSubject.getSubject().getCode())
                .semester(studentSubject.getSemester())
                .grade(studentSubject.getGrade())
                .build();
    }

}
