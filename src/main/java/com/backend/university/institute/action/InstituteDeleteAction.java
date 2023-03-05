package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.exception.InstituteExceptionSupplier;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstituteDeleteAction {

    private final InstituteRepository repository;

    public void delete(Long id) {
        repository.delete(findEntityById(id));
    }

    private Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(InstituteExceptionSupplier.notFoundById(id));
    }

}
