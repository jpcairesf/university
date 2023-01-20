package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Employee;
import com.backend.university.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no employee with ID \"%s\".", id)));
    }

}
