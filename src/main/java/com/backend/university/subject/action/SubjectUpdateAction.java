package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import com.backend.university.subject.exception.SubjectExceptionSupplier;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SubjectUpdateAction {

    private final SubjectRepository repository;

    private final SubjectValidatorAction validatorAction;

    @Transactional
    public SubjectOutputDTO update(SubjectUpdateDTO update) {
        Subject subject = this.findEntityById(update.getId());

        if (!update.getCode().equalsIgnoreCase(subject.getCode())) {
            this.validatorAction.validateExistsByCode(update.getCode());
            subject.setCode(update.getCode());
        }

        subject.setName(update.getName());
        subject.setStudyLoad(update.getStudyLoad());

        repository.save(subject);
        return SubjectMapper.entityToOutput(subject);
    }

    private Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectExceptionSupplier.notFoundById(id));    }

}
