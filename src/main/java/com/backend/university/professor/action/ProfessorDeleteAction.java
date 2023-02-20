package com.backend.university.professor.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

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
                .orElseThrow(() -> new BusinessException(format("There is no professor with ID \"%s\".", id)));
    }

}
