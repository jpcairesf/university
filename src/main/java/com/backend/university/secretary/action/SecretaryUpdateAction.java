package com.backend.university.secretary.action;

import com.backend.university.institute.action.InstituteRelatedAction;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryUpdateDTO;
import com.backend.university.secretary.exception.SecretaryExceptionSupplier;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SecretaryUpdateAction {

    private final SecretaryRepository repository;

    private final SecretaryValidatorAction validatorAction;

    private final InstituteRelatedAction instituteRelatedAction;

    @Transactional
    public Secretary update(SecretaryUpdateDTO update) {
        Secretary secretary = this.findEntityById(update.getId());

        if (!secretary.getInstitute().getName().equalsIgnoreCase(update.getInstitute())) {
            secretary.setInstitute(instituteRelatedAction.findEntityByName(update.getInstitute()));
        }
        if (!update.getCpf().equalsIgnoreCase(secretary.getCpf())) {
            validatorAction.validateExistsByCpf(update.getCpf());
            validatorAction.validateCpf(update.getCpf());
            secretary.setCpf(update.getCpf());
        }
        secretary.setName(update.getName());
        secretary.setEmail(update.getEmail());
        secretary.setBirthDate(update.getBirthDate());
        secretary.setHiringDate(update.getHiringDate());
        secretary.setTenderNotice(update.getTenderNotice());

        return repository.save(secretary);
    }

    private Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SecretaryExceptionSupplier.notFoundById(id));    }

}
