package com.backend.university.dto.mapper;

import com.backend.university.domain.Enrollment;
import com.backend.university.dto.input.EnrollmentInputDTO;
import com.backend.university.dto.output.EnrollmentOutputDTO;
import com.backend.university.dto.output.EnrollmentSubjectOutputDTO;
import com.backend.university.dto.update.EnrollmentUpdateDTO;
import com.backend.university.service.CourseService;
import com.backend.university.service.EnrollmentService;
import com.backend.university.service.StudentService;
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

    private final EnrollmentService enrollmentService;

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

    public Enrollment updateToEntity(EnrollmentUpdateDTO update) {
        Enrollment enrollment = enrollmentService.findEntityById(update.getId());
        if (!enrollment.getStudent().getCpf().equalsIgnoreCase(update.getStudentCpf())) {
            enrollment.setStudent(studentService.findEntityByCpf(update.getStudentCpf()));
        }
        if(!enrollment.getCourse().getName().equalsIgnoreCase(update.getCourse())) {
            enrollment.setCourse(courseService.findEntityByName(update.getCourse()));
        }
        setIfNotNull(update.getNumber(), enrollment::setNumber);
        setIfNotNull(update.getEnrollmentDate(), enrollment::setEnrollmentDate);
        return enrollment;
    }

    public EnrollmentOutputDTO entityToOutput(Enrollment enrollment) {
        List<EnrollmentSubjectOutputDTO> subjects = enrollment.getEnrollmentSubjects().stream()
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
