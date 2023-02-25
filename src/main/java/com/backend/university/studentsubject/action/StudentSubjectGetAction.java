package com.backend.university.studentsubject.action;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.exception.StudentSubjectExceptionSupplier;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentSubjectGetAction {

    private final StudentSubjectRepository repository;

    public StudentSubject findById(StudentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(StudentSubjectExceptionSupplier.notFoundById(id));
    }

    public List<StudentSubject> findAll() {
        return repository.findAll();
    }

}
