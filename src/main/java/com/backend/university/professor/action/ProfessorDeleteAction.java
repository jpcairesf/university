package com.backend.university.professor.action;

import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.exception.ProfessorExceptionSupplier;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProfessorDeleteAction {

    private final ProfessorRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Professor findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(ProfessorExceptionSupplier.notFoundById(id));
    }

}
