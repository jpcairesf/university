package com.backend.university.secretary.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.repository.SecretaryRepository;
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
