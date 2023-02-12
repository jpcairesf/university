package com.backend.university.student.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.service.CourseService;
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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {

    private final StudentRepository repository;

    private final CourseService courseService;

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
        this.validateExistsByNumber(input.getEnrollmentNumber());
        this.validateExistsByCpf(input.getCpf());

        Student student = new Student();
        student.setName(input.getName());
        student.setEmail(input.getEmail());
        student.setCpf(input.getCpf());
        student.setCourse(courseService.findEntityByName(input.getCourse()));
        student.setEnrollmentNumber(input.getEnrollmentNumber());
        student.setEnrollmentDate(input.getEnrollmentDate());

        repository.save(student);
        return StudentMapper.entityToOutput(student);
    }

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        Student student = this.findEntityById(update.getId());

        if (update.getEnrollmentNumber() != student.getEnrollmentNumber()) {
            this.validateExistsByNumber(update.getEnrollmentNumber());
            student.setEnrollmentNumber(update.getEnrollmentNumber());
        }
        if (!update.getCpf().equalsIgnoreCase(student.getCpf())) {
            this.validateExistsByCpf(update.getCpf());
            student.setCpf(update.getCpf());
        }
        if (!update.getCourse().equalsIgnoreCase(student.getCourse().getName())) {
            student.setCourse(courseService.findEntityByName(update.getCourse()));
        }

        student.setName(update.getName());
        student.setEmail(update.getEmail());
        student.setEnrollmentNumber(update.getEnrollmentNumber());
        student.setEnrollmentDate(update.getEnrollmentDate());

        repository.save(student);
        return StudentMapper.entityToOutput(student);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Long findIdByNumber(int number) {
        return repository.findIdByNumber(number)
                .orElseThrow(() -> new BusinessException(format("There is no student with number \"%s\".", number)));
    }

    public StudentOutputDTO findByNumber(int number) {
        Student student = repository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException(""));
        return StudentMapper.entityToOutput(student);
    }

    public Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no student with ID \"%s\".", id)));
    }

    public Student findEntityByNumber(int number) {
        return repository.findByNumber(number)
                .orElseThrow(() -> new BusinessException(format("There is no student with number \"%s\".", number)));
    }

    private void validateExistsByNumber(int number) {
        if (repository.existsByNumber(number)) {
            throw new BusinessException(format("There is already an student with number \"%s\".", number));
        }
    }

    private void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There is already an student with CPF \"%s\".", cpf));
        }
    }

}
