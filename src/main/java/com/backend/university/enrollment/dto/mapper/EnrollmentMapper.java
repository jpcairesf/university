package com.backend.university.enrollment.dto.mapper;

import com.backend.university.enrollmentsubject.dto.mapper.EnrollmentSubjectMapper;
import com.backend.university.enrollment.domain.Enrollment;
import com.backend.university.enrollment.dto.EnrollmentInputDTO;
import com.backend.university.enrollment.dto.EnrollmentOutputDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.enrollment.dto.EnrollmentUpdateDTO;
import com.backend.university.course.service.CourseService;
import com.backend.university.enrollment.service.EnrollmentService;
import com.backend.university.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentMapper {

    private final EnrollmentSubjectMapper enrollmentSubjectMapper;

    private final StudentService studentService;

    private final CourseService courseService;

    public Enrollment inputToEntity(EnrollmentInputDTO input) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentService.findEntityByCpf(input.getStudentCpf()));
        enrollment.setCourse(courseService.findEntityByName(input.getCourse()));
        enrollment.setEnrollmentSubjects(new ArrayList<>());
        enrollment.setNumber(input.getNumber());
        enrollment.setEnrollmentDate(input.getEnrollmentDate());
        return enrollment;
    }

    public EnrollmentOutputDTO entityToOutput(Enrollment enrollment) {
        List<EnrollmentSubjectOutputDTO> subjects =
                enrollment.getEnrollmentSubjects().stream()
                .map(enrollmentSubjectMapper::entityToOutput)
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
