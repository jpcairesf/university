package com.backend.university.subject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

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

    private Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no subject with ID \"%s\".", id)));
    }

}
