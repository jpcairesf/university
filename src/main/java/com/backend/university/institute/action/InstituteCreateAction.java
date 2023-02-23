package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteInputDTO;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.mapper.InstituteMapper;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InstituteCreateAction {

    private final InstituteRepository repository;

    private final InstituteValidatorAction validatorAction;

    @Transactional
    public InstituteOutputDTO create(InstituteInputDTO input) {
        validatorAction.validateExistsByName(input.getName());

        Institute institute = new Institute();
        institute.setName(input.getName());
        institute.setFoundationDate(input.getFoundationDate());

        repository.save(institute);
        return InstituteMapper.entityToOutput(institute);
    }

}
