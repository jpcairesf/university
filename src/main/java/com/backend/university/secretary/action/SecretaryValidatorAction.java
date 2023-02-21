package com.backend.university.secretary.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.secretary.exception.SecretaryExceptionMessages;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretaryValidatorAction {

    private final SecretaryRepository repository;

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(SecretaryExceptionMessages.existsByCpf(cpf));
        }
    }

}
