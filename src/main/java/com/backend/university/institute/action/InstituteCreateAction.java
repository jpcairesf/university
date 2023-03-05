package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteInputDTO;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstituteCreateAction {

    private final InstituteRepository repository;

    private final InstituteValidatorAction validatorAction;

    public Institute create(InstituteInputDTO input) {
        validatorAction.validateExistsByName(input.getName());

        Institute institute = new Institute();
        institute.setName(input.getName());
        institute.setFoundationDate(input.getFoundationDate());

        return repository.save(institute);
    }

}
