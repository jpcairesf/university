package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteUpdateDTO;
import com.backend.university.institute.exception.InstituteExceptionSupplier;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstituteUpdateAction {

    private final InstituteRepository repository;

    private final InstituteValidatorAction validatorAction;

    public Institute update(InstituteUpdateDTO update) {
        Institute institute = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(institute.getName())) {
            validatorAction.validateExistsByName(update.getName());
            institute.setName(update.getName());
        }
        institute.setFoundationDate(update.getFoundationDate());

        return repository.save(institute);
    }

    private Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(InstituteExceptionSupplier.notFoundById(id));
    }

}
