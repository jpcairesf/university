package com.backend.university.studentsubject.dto.mapper;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentSubjectMapper {

    public static StudentSubjectOutputDTO entityToOutput(StudentSubject studentSubject) {
        return StudentSubjectOutputDTO.builder()
                .id(studentSubject.getId())
                .enrollmentNumber(studentSubject.getStudent().getEnrollmentNumber())
                .subjectCode(studentSubject.getSubjectOffer().getSubject().getCode())
                .classNumber(studentSubject.getSubjectOffer().getClassNumber())
                .semester(studentSubject.getSemester())
                .grade(studentSubject.getGrade())
                .build();
    }

}
