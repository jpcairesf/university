package com.backend.university.employee.action;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeCreateAction {

    private final EmployeeRepository repository;

    private final EmployeeValidatorAction validatorAction;

    public Employee create(EmployeeInputDTO input) {
        validatorAction.validateExistsByCpf(input.getCpf());
        validatorAction.validateCpf(input.getCpf());

        Employee employee = new Employee();
        employee.setCpf(input.getCpf());
        employee.setName(input.getName());
        employee.setEmail(input.getEmail());
        employee.setBirthDate(input.getBirthDate());
        employee.setHiringDate(input.getHiringDate());

        return repository.save(employee);
    }

}
