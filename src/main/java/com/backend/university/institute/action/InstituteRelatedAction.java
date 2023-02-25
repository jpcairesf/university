package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.exception.InstituteExceptionSupplier;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstituteRelatedAction {

    private final InstituteRepository repository;

    public Institute findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(InstituteExceptionSupplier.notFoundByName(name));
    }

}
