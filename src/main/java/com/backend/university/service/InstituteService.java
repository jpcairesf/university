package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Institute;
import com.backend.university.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstituteService {

    private final InstituteRepository repository;

    public Institute findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no institute name \"%s\".", name)));
    }

}
