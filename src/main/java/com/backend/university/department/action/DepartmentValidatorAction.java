package com.backend.university.department.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class DepartmentValidatorAction {

    private final DepartmentRepository repository;

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a department named \"%s\".", name));
        }
    }

}
