package com.backend.university.student.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.service.CourseService;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.EnrollmentInputDTO;
import com.backend.university.student.dto.EnrollmentOutputDTO;
import com.backend.university.student.dto.EnrollmentUpdateDTO;
import com.backend.university.student.dto.mapper.EnrollmentMapper;
import com.backend.university.student.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentService {

    private final EnrollmentRepository repository;

    private final StudentService studentService;

    private final CourseService courseService;

    @Transactional
    public EnrollmentOutputDTO findById(Long id) {
        return EnrollmentMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional
    public List<EnrollmentOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(EnrollmentMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public EnrollmentOutputDTO create(EnrollmentInputDTO input) {
        this.validateExistsByNumber(input.getNumber());

        Student student = new Student();
        student.setStudent(studentService.findEntityByCpf(input.getStudentCpf()));
        student.setCourse(courseService.findEntityByName(input.getCourse()));
        student.setEnrollmentSubjects(new ArrayList<>());
        student.setNumber(input.getNumber());
        student.setEnrollmentDate(input.getEnrollmentDate());

        repository.save(student);
        return EnrollmentMapper.entityToOutput(student);
    }

    @Transactional
    public EnrollmentOutputDTO update(EnrollmentUpdateDTO update) {
        Student student = this.findEntityById(update.getId());

        if (!update.getStudentCpf().equalsIgnoreCase(student.getStudent().getCpf())) {
            student.setStudent(studentService.findEntityByCpf(update.getStudentCpf()));
        }
        if (!update.getCourse().equalsIgnoreCase(student.getCourse().getName())) {
            student.setCourse(courseService.findEntityByName(update.getCourse()));
        }
        student.setNumber(update.getNumber());
        student.setEnrollmentDate(update.getEnrollmentDate());

        repository.save(student);
        return EnrollmentMapper.entityToOutput(student);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Long findIdByNumber(int number) {
        return repository.findIdByNumber(number)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment with number \"%s\".", number)));
    }

    public Student findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment with ID \"%s\".", id)));
    }

    public Student findEntityByNumber(int number) {
        return repository.findByNumber(number)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment with number \"%s\".", number)));
    }

    private void validateExistsByNumber(int number) {
        if (repository.existsByNumber(number)) {
            throw new BusinessException(format("There is already an enrollment with number \"%s\".", number));
        }
    }

}
