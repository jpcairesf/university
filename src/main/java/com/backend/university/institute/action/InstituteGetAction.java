package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.mapper.InstituteMapper;
import com.backend.university.institute.exception.InstituteExceptionSupplier;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstituteGetAction {

    private final InstituteRepository repository;

    @Transactional(readOnly = true)
    public InstituteOutputDTO findById(Long id) {
        return InstituteMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<InstituteOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(InstituteMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(InstituteExceptionSupplier.instituteNotFoundById(id));
    }

}
