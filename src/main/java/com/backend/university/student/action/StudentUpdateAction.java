package com.backend.university.student.action;

import com.backend.university.course.action.CourseRelatedAction;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.student.exception.StudentExceptionSupplier;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentUpdateAction {

    private final StudentRepository repository;

    private final StudentValidatorAction validatorAction;

    private final CourseRelatedAction courseRelatedAction;

    public Student update(StudentUpdateDTO update) {
        Student student = this.findEntityById(update.getId());

        if (update.getEnrollmentNumber() != student.getEnrollmentNumber()) {
            validatorAction.validateExistsByNumber(update.getEnrollmentNumber());
            student.setEnrollmentNumber(update.getEnrollmentNumber());
        }
        if (!update.getCpf().equalsIgnoreCase(student.getCpf())) {
            validatorAction.validateExistsByCpf(update.getCpf());
            validatorAction.validateCpf(update.getCpf());
            student.setCpf(update.getCpf());
        }
        if (!update.getCourse().equalsIgnoreCase(student.getCourse().getName())) {
            student.setCourse(courseRelatedAction.findEntityByName(update.getCourse()));
        }

        student.setName(update.getName());
        student.setEmail(update.getEmail());
        student.setEnrollmentNumber(update.getEnrollmentNumber());
        student.setEnrollmentDate(update.getEnrollmentDate());

        return repository.save(student);
    }

    private Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(StudentExceptionSupplier.notFoundById(id));
    }

}
