package com.backend.university.professor.action;

import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.exception.ProfessorExceptionSupplier;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfessorGetAction {

    private final ProfessorRepository repository;

    public Professor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(ProfessorExceptionSupplier.notFoundById(id));
    }

    public List<Professor> findAll() {
        return repository.findAll();
    }

}
