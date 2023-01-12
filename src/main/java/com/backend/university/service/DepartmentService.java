package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Department;
import com.backend.university.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {

    private final DepartmentRepository repository;

    public Department findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no department named \"%s\".", name)));
    }

}
