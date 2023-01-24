package com.backend.university.institute.dto.mapper;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstituteMapper {

    public static InstituteOutputDTO entityToOutput(Institute institute) {
        return InstituteOutputDTO.builder()
                .id(institute.getId())
                .name(institute.getName())
                .foundationDate(institute.getFoundationDate())
                .build();
    }

}
