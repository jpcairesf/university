package com.backend.university.student.action;

import com.backend.university.course.service.CourseService;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.student.dto.mapper.StudentMapper;
import com.backend.university.student.exception.StudentExceptionSupplier;
import com.backend.university.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudentUpdateAction {

    private final StudentRepository repository;

    private final StudentValidatorAction validatorAction;

    private final CourseService courseService;

    @Transactional
    public StudentOutputDTO update(StudentUpdateDTO update) {
        Student student = this.findEntityById(update.getId());

        if (update.getEnrollmentNumber() != student.getEnrollmentNumber()) {
            this.validatorAction.validateExistsByNumber(update.getEnrollmentNumber());
            student.setEnrollmentNumber(update.getEnrollmentNumber());
        }
        if (!update.getCpf().equalsIgnoreCase(student.getCpf())) {
            this.validatorAction.validateExistsByCpf(update.getCpf());
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

    private Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(StudentExceptionSupplier.notFoundById(id));
    }

}
