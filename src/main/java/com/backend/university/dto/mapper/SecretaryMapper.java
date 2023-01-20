package com.backend.university.dto.mapper;

import com.backend.university.domain.Secretary;
import com.backend.university.dto.input.SecretaryInputDTO;
import com.backend.university.dto.output.SecretaryOutputDTO;
import com.backend.university.dto.update.SecretaryUpdateDTO;
import com.backend.university.service.InstituteService;
import com.backend.university.service.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecretaryMapper {

    private final SecretaryService secretaryService;

    private final InstituteService instituteService;

    public Secretary inputToEntity(SecretaryInputDTO input) {
        Secretary secretary = new Secretary();
        secretary.setCpf(input.getCpf());
        secretary.setName(input.getName());
        secretary.setEmail(input.getEmail());
        secretary.setBirthDate(input.getBirthDate());
        secretary.setHiringDate(input.getHiringDate());
        secretary.setTenderNotice(input.getTenderNotice());
        secretary.setInstitute(instituteService.findEntityByName(input.getInstitute()));
        return secretary;
    }

    public Secretary updateToEntity(SecretaryUpdateDTO update) {
        Secretary secretary = secretaryService.findEntityById(update.getId());
        if (!secretary.getInstitute().getName().equalsIgnoreCase(update.getInstitute())) {
            secretary.setInstitute(instituteService.findEntityByName(update.getInstitute()));
        }
        setIfNotNull(update.getName(), secretary::setName);
        setIfNotNull(update.getCpf(), secretary::setCpf);
        setIfNotNull(update.getEmail(), secretary::setEmail);
        setIfNotNull(update.getBirthDate(), secretary::setBirthDate);
        setIfNotNull(update.getHiringDate(), secretary::setHiringDate);
        setIfNotNull(update.getTenderNotice(), secretary::setTenderNotice);
        return secretary;
    }

    public SecretaryOutputDTO entityToOutput(Secretary secretary) {
        return SecretaryOutputDTO.builder()
                .id(secretary.getId())
                .name(secretary.getName())
                .cpf(secretary.getCpf())
                .email(secretary.getEmail())
                .birthDate(secretary.getBirthDate())
                .hiringDate(secretary.getHiringDate())
                .tenderNotice(secretary.getTenderNotice())
                .institute(secretary.getInstitute().getName())
                .build();
    }

}
