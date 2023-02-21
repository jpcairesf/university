package com.backend.university.employee.action;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import com.backend.university.employee.dto.EmployeeUpdateDTO;
import com.backend.university.employee.dto.mapper.EmployeeMapper;
import com.backend.university.employee.exception.EmployeeExceptionSupplier;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmployeeUpdateAction {

    private final EmployeeRepository repository;

    private final EmployeeValidatorAction validatorAction;

    @Transactional
    public EmployeeOutputDTO update(EmployeeUpdateDTO update) {
        Employee employee = this.findEntityById(update.getId());

        if (!update.getCpf().equalsIgnoreCase(employee.getCpf())) {
            this.validatorAction.validateExistsByCpf(update.getCpf());
            employee.setCpf(update.getCpf());
        }
        employee.setName(update.getName());
        employee.setEmail(update.getEmail());
        employee.setBirthDate(update.getBirthDate());
        employee.setHiringDate(update.getHiringDate());

        repository.save(employee);
        return EmployeeMapper.entityToOutput(employee);
    }

    private Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(EmployeeExceptionSupplier.notFoundById(id));
    }

}
