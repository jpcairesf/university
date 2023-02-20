package com.backend.university.student.action;

import com.backend.university.student.domain.Student;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class StudentDeleteAction {

    private final StudentRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no student with ID \"%s\".", id)));
    }

}
