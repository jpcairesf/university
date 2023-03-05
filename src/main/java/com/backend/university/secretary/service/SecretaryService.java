package com.backend.university.secretary.service;

import com.backend.university.secretary.action.SecretaryCreateAction;
import com.backend.university.secretary.action.SecretaryDeleteAction;
import com.backend.university.secretary.action.SecretaryGetAction;
import com.backend.university.secretary.action.SecretaryUpdateAction;
import com.backend.university.secretary.dto.SecretaryInputDTO;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.SecretaryUpdateDTO;
import com.backend.university.secretary.dto.mapper.SecretaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryGetAction getAction;

    private final SecretaryCreateAction createAction;

    private final SecretaryUpdateAction updateAction;

    private final SecretaryDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public SecretaryOutputDTO findById(Long id) {
        return SecretaryMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<SecretaryOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(SecretaryMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public SecretaryOutputDTO create(SecretaryInputDTO input) {
        return SecretaryMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public SecretaryOutputDTO update(SecretaryUpdateDTO update) {
        return SecretaryMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
