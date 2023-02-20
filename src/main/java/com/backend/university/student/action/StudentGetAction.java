package com.backend.university.student.action;

import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.mapper.StudentMapper;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class StudentGetAction {

    private final StudentRepository repository;

    @Transactional(readOnly = true)
    public StudentOutputDTO findById(Long id) {
        return StudentMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<StudentOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(StudentMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no student with ID \"%s\".", id)));
    }

}
