package com.backend.university.professor.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ProfessorValidatorAction {

    private final ProfessorRepository repository;

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There already a professor with CPF \"%s\".", cpf));
        }
    }

}
