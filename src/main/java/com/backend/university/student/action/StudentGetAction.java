package com.backend.university.student.action;

import com.backend.university.student.domain.Student;
import com.backend.university.student.exception.StudentExceptionSupplier;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentGetAction {

    private final StudentRepository repository;

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(StudentExceptionSupplier.notFoundById(id));
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

}
