package com.backend.university.studentsubject.action;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.exception.StudentSubjectExceptionSupplier;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentSubjectDeleteAction {

    private final StudentSubjectRepository repository;

    public void delete(StudentSubjectId id) {
        repository.delete(findEntityById(id));
    }

    private StudentSubject findEntityById(StudentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(StudentSubjectExceptionSupplier.notFoundById(id));
    }

}
