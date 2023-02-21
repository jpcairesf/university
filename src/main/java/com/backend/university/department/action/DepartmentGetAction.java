package com.backend.university.department.action;

import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.mapper.DepartmentMapper;
import com.backend.university.department.exception.DepartmentExceptionSupplier;
import com.backend.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentGetAction {

    private final DepartmentRepository repository;

    @Transactional(readOnly = true)
    public DepartmentOutputDTO findById(Long id) {
        return DepartmentMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<DepartmentOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(DepartmentMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Department findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(DepartmentExceptionSupplier.departmentNotFoundById(id));
    }

}
