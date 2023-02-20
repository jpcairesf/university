package com.backend.university.subject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SubjectDeleteAction {

    private final SubjectRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no subject with ID \"%s\".", id)));
    }

}
