package com.backend.university.student.dto.mapper;

import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.enrollment.service.EnrollmentService;
import com.backend.university.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentMapper {

    private final EnrollmentService enrollmentService;

    public Student inputToEntity(StudentInputDTO input) {
        Student student = new Student();
        student.setCpf(input.getCpf());
        student.setName(input.getName());
        student.setEmail(input.getEmail());
        student.setBirthDate(input.getBirthDate());
        student.setEnrollment(enrollmentService.findEntityByNumber(input.getEnrollmentNumber()));
        return student;
    }

    public StudentOutputDTO entityToOutput(Student student) {
        return StudentOutputDTO.builder()
                .id(student.getId())
                .cpf(student.getCpf())
                .name(student.getName())
                .birthDate(student.getBirthDate())
                .enrollmentNumber(student.getEnrollment().getNumber())
                .build();
    }

}
