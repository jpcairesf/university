package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.exception.InstituteExceptionSupplier;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstituteGetAction {

    private final InstituteRepository repository;

    public Institute findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(InstituteExceptionSupplier.notFoundById(id));
    }

    public List<Institute> findAll() {
        return repository.findAll();
    }

}
