package com.backend.university.employee.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class EmployeeValidatorAction {

    private final EmployeeRepository repository;

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already an employee with CPF \"%s\".", cpf));
        }
    }

}
