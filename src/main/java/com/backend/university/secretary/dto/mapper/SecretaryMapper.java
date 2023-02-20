package com.backend.university.secretary.dto.mapper;

import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecretaryMapper {

    public static SecretaryOutputDTO entityToOutput(Secretary secretary) {
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
