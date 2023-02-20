package com.backend.university.institute.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class InstituteDeleteAction {

    private final InstituteRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(findEntityById(id));
    }

    private Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no institute with ID \"%s\".", id)));
    }

}
