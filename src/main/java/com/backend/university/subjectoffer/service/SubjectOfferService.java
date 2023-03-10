package com.backend.university.subjectoffer.service;

import com.backend.university.subjectoffer.action.SubjectOfferCreateAction;
import com.backend.university.subjectoffer.action.SubjectOfferDeleteAction;
import com.backend.university.subjectoffer.action.SubjectOfferGetAction;
import com.backend.university.subjectoffer.action.SubjectOfferUpdateAction;
import com.backend.university.subjectoffer.dto.SubjectOfferInputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferUpdateDTO;
import com.backend.university.subjectoffer.dto.mapper.SubjectOfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectOfferService {

    private final SubjectOfferGetAction getAction;

    private final SubjectOfferCreateAction createAction;

    private final SubjectOfferUpdateAction updateAction;

    private final SubjectOfferDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public SubjectOfferOutputDTO findById(Long id) {
        return SubjectOfferMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<SubjectOfferOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(SubjectOfferMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubjectOfferOutputDTO create(SubjectOfferInputDTO input) {
        return SubjectOfferMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public SubjectOfferOutputDTO update(SubjectOfferUpdateDTO update) {
        return SubjectOfferMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
