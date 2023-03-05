package com.backend.university.student.action;

import com.backend.university.student.domain.Student;
import com.backend.university.student.exception.StudentExceptionSupplier;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentDeleteAction {

    private final StudentRepository repository;

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(StudentExceptionSupplier.notFoundById(id));
    }

}
