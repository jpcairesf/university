package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Secretary;
import com.backend.university.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecretaryService {

    private final SecretaryRepository repository;

    public Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no secretary with ID \"%s\".", id)));
    }

}
