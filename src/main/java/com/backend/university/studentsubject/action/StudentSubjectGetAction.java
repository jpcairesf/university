package com.backend.university.studentsubject.action;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.mapper.StudentSubjectMapper;
import com.backend.university.studentsubject.exception.StudentSubjectExceptionSupplier;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentSubjectGetAction {

    private final StudentSubjectRepository repository;

    @Transactional(readOnly = true)
    public StudentSubjectOutputDTO findById(StudentSubjectId id) {
        return StudentSubjectMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<StudentSubjectOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(StudentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private StudentSubject findEntityById(StudentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(StudentSubjectExceptionSupplier.notFoundById(id));    }

}
