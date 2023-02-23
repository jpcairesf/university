package com.backend.university.secretary.action;

import com.backend.university.institute.service.InstituteService;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.SecretaryUpdateDTO;
import com.backend.university.secretary.dto.mapper.SecretaryMapper;
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

    private final InstituteService instituteService;

    @Transactional
    public SecretaryOutputDTO update(SecretaryUpdateDTO update) {
        Secretary secretary = this.findEntityById(update.getId());

        if (!secretary.getInstitute().getName().equalsIgnoreCase(update.getInstitute())) {
            secretary.setInstitute(instituteService.findEntityByName(update.getInstitute()));
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

        repository.save(secretary);
        return SecretaryMapper.entityToOutput(secretary);
    }

    private Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SecretaryExceptionSupplier.notFoundById(id));    }

}
