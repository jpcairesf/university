package com.backend.university.secretary.action;

import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.mapper.SecretaryMapper;
import com.backend.university.secretary.exception.SecretaryExceptionSupplier;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SecretaryGetAction {

    private final SecretaryRepository repository;

    @Transactional(readOnly = true)
    public SecretaryOutputDTO findById(Long id) {
        return SecretaryMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<SecretaryOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(SecretaryMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SecretaryExceptionSupplier.notFoundById(id));    }

}
