package com.backend.university.secretary.dto.mapper;

import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryInputDTO;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.SecretaryUpdateDTO;
import com.backend.university.institute.service.InstituteService;
import com.backend.university.secretary.service.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecretaryMapper {

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
