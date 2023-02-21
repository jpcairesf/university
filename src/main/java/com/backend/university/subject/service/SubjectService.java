package com.backend.university.subject.service;

import com.backend.university.subject.action.SubjectCreateAction;
import com.backend.university.subject.action.SubjectDeleteAction;
import com.backend.university.subject.action.SubjectGetAction;
import com.backend.university.subject.action.SubjectUpdateAction;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectGetAction getAction;

    private final SubjectCreateAction createAction;

    private final SubjectUpdateAction updateAction;

    private final SubjectDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public SubjectOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<SubjectOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public Subject findEntityByCode(String code) {
        return this.getAction.findEntityByCode(code);
    }

    @Transactional
    public SubjectOutputDTO create(SubjectInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public SubjectOutputDTO update(SubjectUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

}
