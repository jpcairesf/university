package com.backend.university.employee.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class EmployeeDeleteAction {

    private final EmployeeRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(findEntityById(id));
    }

    private Employee findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no employee with ID \"%s\".", id)));
    }

}
