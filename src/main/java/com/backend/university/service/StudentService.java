package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Student;
import com.backend.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {

    private final StudentRepository repository;

    public Student findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException(format("There is no student with CPF \"%s\".", cpf)));
    }

}
