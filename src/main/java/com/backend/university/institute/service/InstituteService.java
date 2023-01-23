package com.backend.university.institute.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteInputDTO;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.InstituteUpdateDTO;
import com.backend.university.institute.dto.mapper.InstituteMapper;
import com.backend.university.institute.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstituteService {

    private final InstituteRepository repository;

    private final InstituteMapper mapper;

    @Transactional
    public InstituteOutputDTO create(InstituteInputDTO input) {
        this.validateExistsByName(input.getName());
        Institute institute = mapper.inputToEntity(input);
        repository.save(institute);
        return mapper.entityToOutput(institute);
    }

    @Transactional
    public InstituteOutputDTO update(InstituteUpdateDTO update) {
        Institute institute = this.findEntityById(update.getId());
        if (!update.getName().equalsIgnoreCase(institute.getName())) {
            this.validateExistsByName(update.getName());
            institute.setName(update.getName());
        }
        institute.setFoundationDate(update.getFoundationDate());
        repository.save(institute);
        return mapper.entityToOutput(institute);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(findEntityById(id));
    }

    public Institute findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no institute with ID \"%s\".", id)));
    }

    public Institute findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no institute named \"%s\".", name)));
    }

    private void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already an institute named \"%s\".", name));
        }
    }

}
