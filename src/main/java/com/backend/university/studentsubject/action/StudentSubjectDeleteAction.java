package com.backend.university.studentsubject.action;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class StudentSubjectDeleteAction {

    private final StudentSubjectRepository repository;

    @Transactional
    public void delete(StudentSubjectId id) {
        repository.delete(findEntityById(id));
    }

    private StudentSubject findEntityById(StudentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no enrollment subject with ID \"%s\".", id.toString())));
    }

}
