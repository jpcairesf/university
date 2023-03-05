package com.backend.university.professor.action;

import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.exception.ProfessorExceptionSupplier;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfessorRelatedAction {

    private final ProfessorRepository repository;

    public Professor findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(ProfessorExceptionSupplier.notFoundByCpf(cpf));
    }

}
