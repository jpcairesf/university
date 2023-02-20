package com.backend.university.department.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.action.DepartmentCreateAction;
import com.backend.university.department.action.DepartmentDeleteAction;
import com.backend.university.department.action.DepartmentGetAction;
import com.backend.university.department.action.DepartmentUpdateAction;
import com.backend.university.department.domain.Department;
import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.DepartmentUpdateDTO;
import com.backend.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;

    private final DepartmentGetAction getAction;

    private final DepartmentCreateAction createAction;

    private final DepartmentUpdateAction updateAction;

    private final DepartmentDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public DepartmentOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<DepartmentOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public DepartmentOutputDTO create(DepartmentInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public DepartmentOutputDTO update(DepartmentUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

    public Department findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no department named \"%s\".", name)));
    }

}
