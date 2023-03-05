package com.backend.university.professor.service;

import com.backend.university.professor.action.ProfessorCreateAction;
import com.backend.university.professor.action.ProfessorDeleteAction;
import com.backend.university.professor.action.ProfessorGetAction;
import com.backend.university.professor.action.ProfessorUpdateAction;
import com.backend.university.professor.dto.ProfessorInputDTO;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.ProfessorUpdateDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorGetAction getAction;

    private final ProfessorCreateAction createAction;

    private final ProfessorUpdateAction updateAction;

    private final ProfessorDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public ProfessorOutputDTO findById(Long id) {
        return ProfessorMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<ProfessorOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(ProfessorMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfessorOutputDTO create(ProfessorInputDTO input) {
        return ProfessorMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public ProfessorOutputDTO update(ProfessorUpdateDTO update) {
        return ProfessorMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
