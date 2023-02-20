package com.backend.university.student.service;

import com.backend.university.student.action.StudentCreateAction;
import com.backend.university.student.action.StudentDeleteAction;
import com.backend.university.student.action.StudentGetAction;
import com.backend.university.student.action.StudentUpdateAction;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    private final StudentGetAction getAction;

    private final StudentCreateAction createAction;

    private final StudentUpdateAction updateAction;

    private final StudentDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public StudentOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public StudentOutputDTO create(StudentInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

    public Student findEntityByNumber(int number) {
        return repository.findByEnrollmentNumber(number)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no student with number \"%s\".", number)));
    }

}
