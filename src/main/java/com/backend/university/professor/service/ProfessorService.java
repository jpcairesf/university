package com.backend.university.professor.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessorService {

    private final ProfessorRepository repository;

    public Professor findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException(format("There is no professor with CPF \"%s\".", cpf)));
    }

    public Professor findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no professor with ID \"%s\".", id)));
    }
}
