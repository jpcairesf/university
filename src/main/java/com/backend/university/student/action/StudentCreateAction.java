package com.backend.university.student.action;

import com.backend.university.course.action.CourseRelatedAction;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class StudentCreateAction {

    private final StudentRepository repository;

    private final StudentValidatorAction validatorAction;

    private final CourseRelatedAction courseRelatedAction;

    public Student create(StudentInputDTO input) {
        validatorAction.validateExistsByNumber(input.getEnrollmentNumber());
        validatorAction.validateExistsByCpf(input.getCpf());
        validatorAction.validateCpf(input.getCpf());

        Student student = new Student();
        student.setName(input.getName());
        student.setEmail(input.getEmail());
        student.setCpf(input.getCpf());
        student.setCourse(courseRelatedAction.findEntityByName(input.getCourse()));
        student.setEnrollmentNumber(input.getEnrollmentNumber());
        student.setEnrollmentDate(input.getEnrollmentDate());
        student.setStudentSubjects(new HashSet<>());

        return repository.save(student);
    }

}
