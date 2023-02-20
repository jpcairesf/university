package com.backend.university.studentsubject.service;

import com.backend.university.studentsubject.action.StudentSubjectCreateAction;
import com.backend.university.studentsubject.action.StudentSubjectDeleteAction;
import com.backend.university.studentsubject.action.StudentSubjectGetAction;
import com.backend.university.studentsubject.action.StudentSubjectUpdateAction;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectInputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSubjectService {

    private final StudentSubjectGetAction getAction;

    private final StudentSubjectCreateAction createAction;

    private final StudentSubjectUpdateAction updateAction;

    private final StudentSubjectDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public StudentSubjectOutputDTO findById(StudentSubjectId id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentSubjectOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public StudentSubjectOutputDTO create(StudentSubjectInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public StudentSubjectOutputDTO update(StudentSubjectUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(StudentSubjectId id) {
        this.deleteAction.delete(id);
    }

}
