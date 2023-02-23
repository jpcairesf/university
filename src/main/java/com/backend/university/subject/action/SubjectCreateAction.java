package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SubjectCreateAction {

    private final SubjectRepository repository;

    private final SubjectValidatorAction validatorAction;

    @Transactional
    public SubjectOutputDTO create(SubjectInputDTO input) {
        validatorAction.validateExistsByCode(input.getCode());

        Subject subject = new Subject();
        subject.setCode(input.getCode());
        subject.setName(input.getName());
        subject.setStudyLoad(input.getStudyLoad());

        repository.save(subject);
        return SubjectMapper.entityToOutput(subject);
    }

}
