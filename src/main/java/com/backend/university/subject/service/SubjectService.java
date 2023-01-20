package com.backend.university.subject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectService {

    private final SubjectRepository repository;

    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }

    public Long findIdByCode(String code) {
        return repository.findIdByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

    public Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no subject with ID \"%s\".", id)));
    }

    public Subject findEntityByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

}
