package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.exception.SubjectExceptionSupplier;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectRelatedAction {

    private final SubjectRepository repository;

    public Subject findEntityByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(SubjectExceptionSupplier.notFoundByCode(code));
    }

}
