package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.EnrollmentSubject;
import com.backend.university.repository.EnrollmentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentSubjectService {

    private final EnrollmentSubjectRepository repository;

    public EnrollmentSubject findEntityByNumberAndCodeAndSemester(int number, String code, int semester) {
        return repository.findByEnrollmentNumberAndSubjectCodeAndSemester(number, code, semester)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\".", number, code, semester)));
    }

}
