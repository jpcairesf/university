package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.exception.SubjectExceptionSupplier;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubjectGetAction {

    private final SubjectRepository repository;

    public Subject findById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectExceptionSupplier.notFoundById(id));
    }

    public List<Subject> findAll() {
        return repository.findAll();
    }

}
