package com.backend.university.dto.mapper;

import com.backend.university.domain.Student;
import com.backend.university.dto.input.StudentInputDTO;
import com.backend.university.dto.output.StudentOutputDTO;
import com.backend.university.dto.update.StudentUpdateDTO;
import com.backend.university.service.EnrollmentService;
import com.backend.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentMapper {

    private final StudentService studentService;

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

    public Student updateToEntity(StudentUpdateDTO update) {
        Student student = studentService.findEntityById(update.getId());
        if (student.getEnrollment().getNumber() != update.getEnrollmentNumber()) {
            student.setEnrollment(enrollmentService.findEntityByNumber(update.getEnrollmentNumber()));
        }
        setIfNotNull(update.getCpf(), student::setCpf);
        setIfNotNull(update.getName(), student::setName);
        setIfNotNull(update.getEmail(), student::setEmail);
        setIfNotNull(update.getBirthDate(), student::setBirthDate);
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
