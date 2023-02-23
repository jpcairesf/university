package com.backend.university.professor.service;

import com.backend.university.professor.action.ProfessorCreateAction;
import com.backend.university.professor.action.ProfessorDeleteAction;
import com.backend.university.professor.action.ProfessorGetAction;
import com.backend.university.professor.action.ProfessorUpdateAction;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorInputDTO;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.ProfessorUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorGetAction getAction;

    private final ProfessorCreateAction createAction;

    private final ProfessorUpdateAction updateAction;

    private final ProfessorDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public ProfessorOutputDTO findById(Long id) {
        return getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProfessorOutputDTO> findAll() {
        return getAction.findAll();
    }

    @Transactional
    public Professor findEntityByCpf(String cpf) {
        return getAction.findEntityByCpf(cpf);
    }

    @Transactional
    public ProfessorOutputDTO create(ProfessorInputDTO input) {
        return createAction.create(input);
    }

    @Transactional
    public ProfessorOutputDTO update(ProfessorUpdateDTO update) {
        return updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
