package com.backend.university.dto.mapper;

import com.backend.university.domain.Institute;
import com.backend.university.dto.input.InstituteInputDTO;
import com.backend.university.dto.output.InstituteOutputDTO;
import com.backend.university.dto.update.InstituteUpdateDTO;
import com.backend.university.service.InstituteService;
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
