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

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeRepository repository;

    @Transactional
    public EmployeeOutputDTO findById(Long id) {
        return EmployeeMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional
    public List<EmployeeOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(EmployeeMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeOutputDTO create(EmployeeInputDTO input) {
        this.validateExistsByCpf(input.getCpf());

        Employee employee = new Employee();
        employee.setCpf(input.getCpf());
        employee.setName(input.getName());
        employee.setEmail(input.getEmail());
        employee.setBirthDate(input.getBirthDate());
        employee.setHiringDate(input.getHiringDate());

        repository.save(employee);
        return EmployeeMapper.entityToOutput(employee);
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

        repository.save(employee);
        return EmployeeMapper.entityToOutput(employee);
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
