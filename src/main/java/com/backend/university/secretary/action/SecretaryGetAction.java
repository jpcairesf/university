package com.backend.university.secretary.action;

import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.exception.SecretaryExceptionSupplier;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecretaryGetAction {

    private final SecretaryRepository repository;

    public Secretary findById(Long id) {
        return repository.findById(id)
                .orElseThrow(SecretaryExceptionSupplier.notFoundById(id));
    }

    public List<Secretary> findAll() {
        return repository.findAll();
    }

}
