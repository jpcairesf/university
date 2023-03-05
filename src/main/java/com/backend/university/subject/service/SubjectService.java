package com.backend.university.subject.service;

import com.backend.university.subject.action.SubjectCreateAction;
import com.backend.university.subject.action.SubjectDeleteAction;
import com.backend.university.subject.action.SubjectGetAction;
import com.backend.university.subject.action.SubjectUpdateAction;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectGetAction getAction;

    private final SubjectCreateAction createAction;

    private final SubjectUpdateAction updateAction;

    private final SubjectDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public SubjectOutputDTO findById(Long id) {
        return SubjectMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<SubjectOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(SubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubjectOutputDTO create(SubjectInputDTO input) {
        return SubjectMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public SubjectOutputDTO update(SubjectUpdateDTO update) {
        return SubjectMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
