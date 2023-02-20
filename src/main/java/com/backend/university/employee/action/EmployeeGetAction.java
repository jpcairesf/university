package com.backend.university.employee.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import com.backend.university.employee.dto.mapper.EmployeeMapper;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class EmployeeGetAction {

    private final EmployeeRepository repository;

    @Transactional(readOnly = true)
    public EmployeeOutputDTO findById(Long id) {
        return EmployeeMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(EmployeeMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no employee with ID \"%s\".", id)));
    }

}