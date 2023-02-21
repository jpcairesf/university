package com.backend.university.secretary.action;

import com.backend.university.institute.service.InstituteService;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryInputDTO;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.mapper.SecretaryMapper;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SecretaryCreateAction {

    private final SecretaryRepository repository;

    private final SecretaryValidatorAction validatorAction;

    private final InstituteService instituteService;

    @Transactional
    public SecretaryOutputDTO create(SecretaryInputDTO input) {
        this.validatorAction.validateCpf(input.getCpf());
        this.validatorAction.validateExistsByCpf(input.getCpf());

        Secretary secretary = new Secretary();
        secretary.setCpf(input.getCpf());
        secretary.setName(input.getName());
        secretary.setEmail(input.getEmail());
        secretary.setBirthDate(input.getBirthDate());
        secretary.setHiringDate(input.getHiringDate());
        secretary.setTenderNotice(input.getTenderNotice());
        secretary.setInstitute(instituteService.findEntityByName(input.getInstitute()));

        repository.save(secretary);
        return SecretaryMapper.entityToOutput(secretary);
    }

}
