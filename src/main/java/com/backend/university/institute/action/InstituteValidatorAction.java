package com.backend.university.institute.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.institute.exception.InstituteExceptionMessages;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstituteValidatorAction {

    private final InstituteRepository repository;

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(InstituteExceptionMessages.existsByName(name));
        }
    }

}
