package com.backend.university.subject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.subject.exception.SubjectExceptionMessages;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectValidatorAction {

    private final SubjectRepository repository;

    public void validateExistsByCode(String code) {
        if (repository.existsByCode(code)) {
            throw new BusinessException(SubjectExceptionMessages.existsByCode(code));
        }
    }

}