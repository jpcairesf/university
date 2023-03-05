package com.backend.university.department.service;

import com.backend.university.department.action.DepartmentCreateAction;
import com.backend.university.department.action.DepartmentDeleteAction;
import com.backend.university.department.action.DepartmentGetAction;
import com.backend.university.department.action.DepartmentUpdateAction;
import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.DepartmentUpdateDTO;
import com.backend.university.department.dto.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentGetAction getAction;

    private final DepartmentCreateAction createAction;

    private final DepartmentUpdateAction updateAction;

    private final DepartmentDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public DepartmentOutputDTO findById(Long id) {
        return DepartmentMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<DepartmentOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(DepartmentMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public DepartmentOutputDTO create(DepartmentInputDTO input) {
        return DepartmentMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public DepartmentOutputDTO update(DepartmentUpdateDTO update) {
        return DepartmentMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
