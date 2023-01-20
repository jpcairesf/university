package com.backend.university.enrollmentsubject.dto.mapper;

import com.backend.university.enrollmentsubject.domain.EnrollmentSubject;
import com.backend.university.enrollmentsubject.domain.id.EnrollmentSubjectId;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectInputDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectUpdateDTO;
import com.backend.university.enrollment.service.EnrollmentService;
import com.backend.university.enrollmentsubject.service.EnrollmentSubjectService;
import com.backend.university.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentSubjectMapper {

    private final EnrollmentSubjectService enrollmentSubjectService;

    private final EnrollmentService enrollmentService;

    private final SubjectService subjectService;

    public EnrollmentSubject inputToEntity(EnrollmentSubjectInputDTO input) {
        EnrollmentSubject enrollmentSubject = new EnrollmentSubject();
        enrollmentSubject.setId(new EnrollmentSubjectId(
                enrollmentService.findIdByNumber(input.getEnrollmentNumber()),
                subjectService.findIdByCode(input.getSubjectCode())));
        enrollmentSubject.setSemester(input.getSemester());
        return enrollmentSubject;
    }

    public EnrollmentSubject updateToEntity(EnrollmentSubjectUpdateDTO update) {
        EnrollmentSubject enrollmentSubject =
                enrollmentSubjectService.findEntityByNumberAndCodeAndSemester(
                update.getEnrollmentNumber(),
                update.getSubjectCode(),
                update.getSemester());

        setIfNotNull(update.getSemester(), enrollmentSubject::setSemester);
        setIfNotNull(update.getGrade(), enrollmentSubject::setGrade);
        return enrollmentSubject;
    }

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
