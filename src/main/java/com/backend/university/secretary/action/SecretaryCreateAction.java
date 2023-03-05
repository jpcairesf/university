package com.backend.university.secretary.action;

import com.backend.university.institute.action.InstituteRelatedAction;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryInputDTO;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretaryCreateAction {

    private final SecretaryRepository repository;

    private final SecretaryValidatorAction validatorAction;

    private final InstituteRelatedAction instituteRelatedAction;

    public Secretary create(SecretaryInputDTO input) {
        validatorAction.validateExistsByCpf(input.getCpf());
        validatorAction.validateCpf(input.getCpf());

        Secretary secretary = new Secretary();
        secretary.setCpf(input.getCpf());
        secretary.setName(input.getName());
        secretary.setEmail(input.getEmail());
        secretary.setBirthDate(input.getBirthDate());
        secretary.setHiringDate(input.getHiringDate());
        secretary.setTenderNotice(input.getTenderNotice());
        secretary.setInstitute(instituteRelatedAction.findEntityByName(input.getInstitute()));

        return repository.save(secretary);
    }

}
