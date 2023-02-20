package com.backend.university.employee.action;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import com.backend.university.employee.dto.mapper.EmployeeMapper;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmployeeCreateAction {

    private final EmployeeRepository repository;

    private final EmployeeValidatorAction validatorAction;

    @Transactional
    public EmployeeOutputDTO create(EmployeeInputDTO input) {
        this.validatorAction.validateExistsByCpf(input.getCpf());

        Employee employee = new Employee();
        employee.setCpf(input.getCpf());
        employee.setName(input.getName());
        employee.setEmail(input.getEmail());
        employee.setBirthDate(input.getBirthDate());
        employee.setHiringDate(input.getHiringDate());

        repository.save(employee);
        return EmployeeMapper.entityToOutput(employee);
    }

}
