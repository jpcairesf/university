package com.backend.university.student.service;

import com.backend.university.common.error.BusinessException;
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

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {

    private final StudentRepository repository;

    @Transactional
    public StudentOutputDTO findById(Long id) {
        return StudentMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional
    public List<StudentOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(StudentMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentOutputDTO create(StudentInputDTO input) {
        this.validateExistsByCpf(input.getCpf());

        Student student = new Student();
        student.setCpf(input.getCpf());
        student.setName(input.getName());
        student.setEmail(input.getEmail());
        student.setBirthDate(input.getBirthDate());

        repository.save(student);
        return StudentMapper.entityToOutput(student);
    }

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        Student student = this.findEntityById(update.getId());

        if (!update.getCpf().equalsIgnoreCase(student.getCpf())) {
            this.validateExistsByCpf(update.getCpf());
            student.setCpf(update.getCpf());
        }
        student.setName(update.getName());
        student.setEmail(update.getEmail());
        student.setBirthDate(update.getBirthDate());

        repository.save(student);
        return StudentMapper.entityToOutput(student);
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
        return repository.findByCpfAndEnrollmentIsNull(cpf)
                .orElseThrow(() -> new BusinessException(format("The student with CPF \"%s\" already has an enrollment.", cpf)));
    }

    private void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already a student with CPF \"%s\".", cpf));
        }
    }

}
