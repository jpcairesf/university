package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.exception.SubjectExceptionSupplier;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectDeleteAction {

    private final SubjectRepository repository;

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectExceptionSupplier.notFoundById(id));    }

}
