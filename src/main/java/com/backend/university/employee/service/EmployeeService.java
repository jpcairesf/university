package com.backend.university.employee.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import com.backend.university.employee.dto.EmployeeUpdateDTO;
import com.backend.university.employee.dto.mapper.EmployeeMapper;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeRepository repository;

    private final EmployeeMapper mapper;

    @Transactional
    public EmployeeOutputDTO create(EmployeeInputDTO input) {
        this.validateExistsByCpf(input.getCpf());
        Employee employee = mapper.inputToEntity(input);
        repository.save(employee);
        return mapper.entityToOutput(employee);
    }

    @Transactional
    public EmployeeOutputDTO update(EmployeeUpdateDTO update) {
        Employee employee = this.findEntityById(update.getId());
        if (!update.getCpf().equalsIgnoreCase(employee.getCpf())) {
            this.validateExistsByCpf(update.getCpf());
            employee.setCpf(update.getCpf());
        }
        employee.setName(update.getName());
        employee.setEmail(update.getEmail());
        employee.setBirthDate(update.getBirthDate());
        employee.setHiringDate(update.getHiringDate());
        return mapper.entityToOutput(employee);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(findEntityById(id));
    }

    public Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no employee with ID \"%s\".", id)));
    }

    private void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already an employee with CPF \"%s\".", cpf));
        }
    }

}
