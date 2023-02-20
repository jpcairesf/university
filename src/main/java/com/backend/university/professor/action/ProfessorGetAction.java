package com.backend.university.professor.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ProfessorGetAction {

    private final ProfessorRepository repository;

    @Transactional(readOnly = true)
    public ProfessorOutputDTO findById(Long id) {
        return ProfessorMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<ProfessorOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(ProfessorMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Professor findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no professor with ID \"%s\".", id)));
    }

}
