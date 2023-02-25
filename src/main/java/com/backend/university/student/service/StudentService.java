package com.backend.university.student.service;

import com.backend.university.student.action.StudentCreateAction;
import com.backend.university.student.action.StudentDeleteAction;
import com.backend.university.student.action.StudentGetAction;
import com.backend.university.student.action.StudentUpdateAction;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.student.dto.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentGetAction getAction;

    private final StudentCreateAction createAction;

    private final StudentUpdateAction updateAction;

    private final StudentDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public StudentOutputDTO findById(Long id) {
        return StudentMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<StudentOutputDTO> findAll() {
        return getAction.findAll().stream()
                .map(StudentMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentOutputDTO create(StudentInputDTO input) {
        return StudentMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        return StudentMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
