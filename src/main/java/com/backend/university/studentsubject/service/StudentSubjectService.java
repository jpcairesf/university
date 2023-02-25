package com.backend.university.studentsubject.service;

import com.backend.university.studentsubject.action.StudentSubjectCreateAction;
import com.backend.university.studentsubject.action.StudentSubjectDeleteAction;
import com.backend.university.studentsubject.action.StudentSubjectGetAction;
import com.backend.university.studentsubject.action.StudentSubjectUpdateAction;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectInputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectUpdateDTO;
import com.backend.university.studentsubject.dto.mapper.StudentSubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentSubjectService {

    private final StudentSubjectGetAction getAction;

    private final StudentSubjectCreateAction createAction;

    private final StudentSubjectUpdateAction updateAction;

    private final StudentSubjectDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public StudentSubjectOutputDTO findById(StudentSubjectId id) {
        return StudentSubjectMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<StudentSubjectOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(StudentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentSubjectOutputDTO create(StudentSubjectInputDTO input) {
        return StudentSubjectMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public StudentSubjectOutputDTO update(StudentSubjectUpdateDTO update) {
        return StudentSubjectMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(StudentSubjectId id) {
        deleteAction.delete(id);
    }

}
