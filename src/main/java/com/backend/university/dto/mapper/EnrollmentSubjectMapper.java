package com.backend.university.dto.mapper;

import com.backend.university.domain.EnrollmentSubject;
import com.backend.university.dto.output.EnrollmentSubjectOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentSubjectMapper {

    public EnrollmentSubjectOutputDTO entityToOutput(EnrollmentSubject enrollmentSubject) {
        return EnrollmentSubjectOutputDTO.builder()
                .id(enrollmentSubject.getId())
                .enrollmentNumber(enrollmentSubject.getEnrollment().getNumber())
                .subjectCode(enrollmentSubject.getSubject().getCode())
                .semester(enrollmentSubject.getSemester())
                .grade(enrollmentSubject.getGrade())
                .build();
    }

}
