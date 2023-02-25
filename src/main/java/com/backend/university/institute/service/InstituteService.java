package com.backend.university.institute.service;

import com.backend.university.institute.action.InstituteCreateAction;
import com.backend.university.institute.action.InstituteDeleteAction;
import com.backend.university.institute.action.InstituteGetAction;
import com.backend.university.institute.action.InstituteUpdateAction;
import com.backend.university.institute.dto.InstituteInputDTO;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.InstituteUpdateDTO;
import com.backend.university.institute.dto.mapper.InstituteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstituteService {

    private final InstituteGetAction getAction;

    private final InstituteCreateAction createAction;

    private final InstituteUpdateAction updateAction;

    private final InstituteDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public InstituteOutputDTO findById(Long id) {
        return InstituteMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<InstituteOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(InstituteMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public InstituteOutputDTO create(InstituteInputDTO input) {
        return InstituteMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public InstituteOutputDTO update(InstituteUpdateDTO update) {
        return InstituteMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
