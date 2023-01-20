package com.backend.university.institute.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstituteService {

    private final InstituteRepository repository;

    public Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no institute with ID \"%s\".", id)));
    }

    public Institute findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no institute named \"%s\".", name)));
    }

}
