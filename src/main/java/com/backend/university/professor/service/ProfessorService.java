package com.backend.university.professor.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.service.DepartmentService;
import com.backend.university.professor.action.ProfessorCreateAction;
import com.backend.university.professor.action.ProfessorDeleteAction;
import com.backend.university.professor.action.ProfessorGetAction;
import com.backend.university.professor.action.ProfessorUpdateAction;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorInputDTO;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.ProfessorUpdateDTO;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    private final DepartmentService departmentService;

    private final ProfessorGetAction getAction;

    private final ProfessorCreateAction createAction;

    private final ProfessorUpdateAction updateAction;

    private final ProfessorDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public ProfessorOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProfessorOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public ProfessorOutputDTO create(ProfessorInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public ProfessorOutputDTO update(ProfessorUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

    public Professor findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException(format("There is no professor with CPF \"%s\".", cpf)));
    }

}
