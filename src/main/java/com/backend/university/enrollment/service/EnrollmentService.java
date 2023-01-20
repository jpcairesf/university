package com.backend.university.enrollment.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.enrollment.domain.Enrollment;
import com.backend.university.enrollment.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public Long findIdByNumber(int number) {
        return repository.findIdByNumber(number)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment with number \"%s\".", number)));
    }

    public Enrollment findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment with ID \"%s\".", id)));
    }

    public Enrollment findEntityByNumber(int number) {
        return repository.findByNumber(number)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment with number \"%s\".", number)));
    }

}
