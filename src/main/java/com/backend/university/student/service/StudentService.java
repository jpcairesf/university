package com.backend.university.student.service;

import com.backend.university.student.action.StudentCreateAction;
import com.backend.university.student.action.StudentDeleteAction;
import com.backend.university.student.action.StudentGetAction;
import com.backend.university.student.action.StudentUpdateAction;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentGetAction getAction;

    private final StudentCreateAction createAction;

    private final StudentUpdateAction updateAction;

    private final StudentDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public StudentOutputDTO findById(Long id) {
        return getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentOutputDTO> findAll() {
        return getAction.findAll();
    }

    @Transactional
    public Student findEntityByNumber(int number) {
        return getAction.findEntityByNumber(number);
    }

    @Transactional
    public StudentOutputDTO create(StudentInputDTO input) {
        return createAction.create(input);
    }

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        return updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
