package com.backend.university.student.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.enrollment.service.EnrollmentService;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.student.dto.mapper.StudentMapper;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {

    private final StudentRepository repository;

    private final StudentMapper mapper;

    private final EnrollmentService enrollmentService;

    @Transactional
    public StudentOutputDTO create(StudentInputDTO input) {
        this.validateExistsByCpf(input.getCpf());
        Student student = mapper.inputToEntity(input);
        repository.save(student);
        return mapper.entityToOutput(student);
    }

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        Student student = this.findEntityById(update.getId());

        if (!update.getCpf().equalsIgnoreCase(student.getCpf())) {
            this.validateExistsByCpf(update.getCpf());
            student.setCpf(update.getCpf());
        }
        if (update.getEnrollmentNumber() != student.getEnrollment().getNumber()) {
            student.setEnrollment(enrollmentService.findEntityByNumber(update.getEnrollmentNumber()));
        }
        student.setName(update.getName());
        student.setEmail(update.getEmail());
        student.setBirthDate(update.getBirthDate());
        repository.save(student);
        return mapper.entityToOutput(student);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no student with ID \"%s\".", id)));
    }

    public Student findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException(format("There is no student with CPF \"%s\".", cpf)));
    }

    public Student findEntityByCfpWithoutEnrollment(String cpf) {
        Student student = this.findEntityByCpf(cpf);
        if (Optional.ofNullable(student.getEnrollment()).isEmpty()) {
            throw new BusinessException(format("The student with CPF \"%s\" already has an enrollment.", cpf));
        }
        return student;
    }

    private void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already a student with CPF \"%s\".", cpf));
        }
    }

}
