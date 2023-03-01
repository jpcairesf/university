package com.backend.university.student.action;

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

    // TODO: create course changing service.
    // boolean ActiveEnrollment -> default true, set false if don't have
    // any subject related in actual semester
    // can change course only if ActiveEnrollment = false
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
