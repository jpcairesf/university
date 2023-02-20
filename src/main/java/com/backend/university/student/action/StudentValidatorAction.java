package com.backend.university.student.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class StudentValidatorAction {

    private final StudentRepository repository;

    public void validateExistsByNumber(int number) {
        if (repository.existsByEnrollmentNumber(number)) {
            throw new BusinessException(format("There is already an student with number \"%s\".", number));
        }
    }

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already an student with CPF \"%s\".", cpf));
        }
    }

}