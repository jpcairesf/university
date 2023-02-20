package com.backend.university.subject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SubjectValidatorAction {

    private final SubjectRepository repository;

    public void validateExistsByCode(String code) {
        if (repository.existsByCode(code)) {
            throw new BusinessException(format("There is already a subject with code \"%s\".", code));
        }
    }

}