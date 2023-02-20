package com.backend.university.student.action;

import com.backend.university.course.service.CourseService;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.mapper.StudentMapper;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class StudentCreateAction {

    private final StudentRepository repository;

    private final StudentValidatorAction validatorAction;

    private final CourseService courseService;

    @Transactional
    public StudentOutputDTO create(StudentInputDTO input) {
        this.validatorAction.validateExistsByNumber(input.getEnrollmentNumber());
        this.validatorAction.validateExistsByCpf(input.getCpf());

        Student student = new Student();
        student.setName(input.getName());
        student.setEmail(input.getEmail());
        student.setCpf(input.getCpf());
        student.setCourse(courseService.findEntityByName(input.getCourse()));
        student.setEnrollmentNumber(input.getEnrollmentNumber());
        student.setEnrollmentDate(input.getEnrollmentDate());
        student.setStudentSubjects(new HashSet<>());

        repository.save(student);
        return StudentMapper.entityToOutput(student);
    }

}
