package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import com.backend.university.subject.exception.SubjectExceptionSupplier;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubjectGetAction {

    private final SubjectRepository repository;

    @Transactional(readOnly = true)
    public SubjectOutputDTO findById(Long id) {
        return SubjectMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<SubjectOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(SubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public Subject findEntityByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(SubjectExceptionSupplier.notFoundByCode(code));
    }

    private Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectExceptionSupplier.notFoundById(id));
    }

}
