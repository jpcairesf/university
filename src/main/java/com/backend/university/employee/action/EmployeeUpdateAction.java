package com.backend.university.employee.action;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeUpdateDTO;
import com.backend.university.employee.exception.EmployeeExceptionSupplier;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeUpdateAction {

    private final EmployeeRepository repository;

    private final EmployeeValidatorAction validatorAction;

    public Employee update(EmployeeUpdateDTO update) {
        Employee employee = this.findEntityById(update.getId());

        if (!update.getCpf().equalsIgnoreCase(employee.getCpf())) {
            validatorAction.validateExistsByCpf(update.getCpf());
            validatorAction.validateCpf(update.getCpf());
            employee.setCpf(update.getCpf());
        }
        employee.setName(update.getName());
        employee.setEmail(update.getEmail());
        employee.setBirthDate(update.getBirthDate());
        employee.setHiringDate(update.getHiringDate());

        return repository.save(employee);
    }

    private Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(EmployeeExceptionSupplier.notFoundById(id));
    }

}
