package com.backend.university.employee.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.common.validator.CPFValidator;
import com.backend.university.employee.exception.EmployeeExceptionMessages;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidatorAction {

    private final EmployeeRepository repository;

    private final CPFValidator cpfValidator;

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(EmployeeExceptionMessages.existsByCpf(cpf));
        }
    }

    public void validateCpf(String cpf) {
        cpfValidator.validate(cpf);
    }

}
