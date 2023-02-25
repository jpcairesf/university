package com.backend.university.student.action;

import com.backend.university.student.domain.Student;
import com.backend.university.student.exception.StudentExceptionSupplier;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRelatedAction {

    private final StudentRepository repository;

    public Student findEntityByNumber(int number) {
        return repository.findByEnrollmentNumber(number)
                .orElseThrow(StudentExceptionSupplier.notFoundByEnrollment(number));
    }

}
