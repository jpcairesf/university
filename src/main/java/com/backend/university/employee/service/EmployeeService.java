package com.backend.university.employee.service;

import com.backend.university.employee.action.EmployeeCreateAction;
import com.backend.university.employee.action.EmployeeDeleteAction;
import com.backend.university.employee.action.EmployeeGetAction;
import com.backend.university.employee.action.EmployeeUpdateAction;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import com.backend.university.employee.dto.EmployeeUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeGetAction getAction;

    private final EmployeeCreateAction createAction;

    private final EmployeeUpdateAction updateAction;

    private final EmployeeDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public EmployeeOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<EmployeeOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public EmployeeOutputDTO create(EmployeeInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public EmployeeOutputDTO update(EmployeeUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

}
