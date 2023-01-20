package com.backend.university.institute.dto.mapper;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteInputDTO;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.InstituteUpdateDTO;
import com.backend.university.institute.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstituteMapper {

    private final InstituteService instituteService;

    public Institute inputToEntity(InstituteInputDTO input) {
        Institute institute = new Institute();
        institute.setName(input.getName());
        institute.setFoundationDate(input.getFoundationDate());
        return institute;
    }

    public Institute updateToEntity(InstituteUpdateDTO update) {
        Institute institute = instituteService.findEntityById(update.getId());
        setIfNotNull(update.getName(), institute::setName);
        setIfNotNull(update.getFoundationDate(), institute::setFoundationDate);
        return institute;
    }

    public InstituteOutputDTO entityToOutput(Institute institute) {
        return InstituteOutputDTO.builder()
                .id(institute.getId())
                .name(institute.getName())
                .foundationDate(institute.getFoundationDate())
                .build();
    }

}
