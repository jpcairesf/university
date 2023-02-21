package com.backend.university.professor.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.common.validator.CPFValidator;
import com.backend.university.professor.exception.ProfessorExceptionMessages;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfessorValidatorAction {

    private final ProfessorRepository repository;

    private final CPFValidator cpfValidator;

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(ProfessorExceptionMessages.existsByCpf(cpf));
        }
    }

    public void validateCpf(String cpf) {
        cpfValidator.validate(cpf);
    }
}
