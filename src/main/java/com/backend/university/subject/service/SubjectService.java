package com.backend.university.subject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.subject.action.SubjectCreateAction;
import com.backend.university.subject.action.SubjectDeleteAction;
import com.backend.university.subject.action.SubjectGetAction;
import com.backend.university.subject.action.SubjectUpdateAction;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;

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

    public Subject findEntityByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

}
