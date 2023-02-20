package com.backend.university.institute.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class InstituteValidatorAction {

    private final InstituteRepository repository;

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already an institute named \"%s\".", name));
        }
    }

}
