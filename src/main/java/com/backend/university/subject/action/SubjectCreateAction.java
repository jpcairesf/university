package com.backend.university.subject.action;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectCreateAction {

    private final SubjectRepository repository;

    private final SubjectValidatorAction validatorAction;

    public Subject create(SubjectInputDTO input) {
        validatorAction.validateExistsByCode(input.getCode());

        Subject subject = new Subject();
        subject.setCode(input.getCode());
        subject.setName(input.getName());
        subject.setStudyLoad(input.getStudyLoad());

        return repository.save(subject);
    }

}
