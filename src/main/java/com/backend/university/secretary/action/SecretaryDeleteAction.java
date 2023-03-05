package com.backend.university.secretary.action;

import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.exception.SecretaryExceptionSupplier;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretaryDeleteAction {

    private final SecretaryRepository repository;

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SecretaryExceptionSupplier.notFoundById(id));
    }

}
