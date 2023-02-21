package com.backend.university.student.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.student.exception.StudentExceptionMessages;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentValidatorAction {

    private final StudentRepository repository;

    public void validateExistsByNumber(int number) {
        if (repository.existsByEnrollmentNumber(number)) {
            throw new BusinessException(StudentExceptionMessages.existsByEnrollment(number));
        }
    }

    public void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(StudentExceptionMessages.existsByCpf(cpf));
        }
    }

}