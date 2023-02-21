package com.backend.university.institute.action;

import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.InstituteUpdateDTO;
import com.backend.university.institute.dto.mapper.InstituteMapper;
import com.backend.university.institute.exception.InstituteExceptionSupplier;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InstituteUpdateAction {

    private final InstituteRepository repository;

    private final InstituteValidatorAction validatorAction;

    @Transactional
    public InstituteOutputDTO update(InstituteUpdateDTO update) {
        Institute institute = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(institute.getName())) {
            this.validatorAction.validateExistsByName(update.getName());
            institute.setName(update.getName());
        }
        institute.setFoundationDate(update.getFoundationDate());

        repository.save(institute);
        return InstituteMapper.entityToOutput(institute);
    }

    private Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(InstituteExceptionSupplier.notFoundById(id));
    }

}
