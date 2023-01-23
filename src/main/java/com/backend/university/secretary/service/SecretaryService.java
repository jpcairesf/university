package com.backend.university.secretary.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.institute.service.InstituteService;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryInputDTO;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.SecretaryUpdateDTO;
import com.backend.university.secretary.dto.mapper.SecretaryMapper;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecretaryService {

    private final SecretaryRepository repository;

    private final SecretaryMapper mapper;

    private final InstituteService instituteService;

    @Transactional
    public SecretaryOutputDTO create(SecretaryInputDTO input) {
        this.validateExistsByCpf(input.getCpf());
        Secretary secretary = mapper.inputToEntity(input);
        repository.save(secretary);
        return mapper.entityToOutput(secretary);
    }

    @Transactional
    public SecretaryOutputDTO update(SecretaryUpdateDTO update) {
        Secretary secretary = this.findEntityById(update.getId());

        if (!secretary.getInstitute().getName().equalsIgnoreCase(update.getInstitute())) {
            secretary.setInstitute(instituteService.findEntityByName(update.getInstitute()));
        }
        if (!update.getCpf().equalsIgnoreCase(secretary.getCpf())) {
            this.validateExistsByCpf(update.getCpf());
            secretary.setCpf(update.getCpf());
        }
        secretary.setName(update.getName());
        secretary.setEmail(update.getEmail());
        secretary.setBirthDate(update.getBirthDate());
        secretary.setHiringDate(update.getHiringDate());
        secretary.setTenderNotice(update.getTenderNotice());
        repository.save(secretary);
        return mapper.entityToOutput(secretary);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no secretary with ID \"%s\".", id)));
    }

    public Secretary findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException(format("There is no secretary with CPF \"%s\".", cpf)));
    }

    private void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already a secretary with CPF \"%s\".", cpf));
        }
    }

}
