package com.backend.university.employee.service;

import com.backend.university.employee.action.EmployeeCreateAction;
import com.backend.university.employee.action.EmployeeDeleteAction;
import com.backend.university.employee.action.EmployeeGetAction;
import com.backend.university.employee.action.EmployeeUpdateAction;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import com.backend.university.employee.dto.EmployeeUpdateDTO;
import com.backend.university.employee.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeGetAction getAction;

    private final EmployeeCreateAction createAction;

    private final EmployeeUpdateAction updateAction;

    private final EmployeeDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public EmployeeOutputDTO findById(Long id) {
        return EmployeeMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(EmployeeMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeOutputDTO create(EmployeeInputDTO input) {
        return EmployeeMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public EmployeeOutputDTO update(EmployeeUpdateDTO update) {
        return EmployeeMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
