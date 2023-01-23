package com.backend.university.enrollment.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.service.CourseService;
import com.backend.university.enrollment.domain.Enrollment;
import com.backend.university.enrollment.dto.EnrollmentInputDTO;
import com.backend.university.enrollment.dto.EnrollmentOutputDTO;
import com.backend.university.enrollment.dto.EnrollmentUpdateDTO;
import com.backend.university.enrollment.dto.mapper.EnrollmentMapper;
import com.backend.university.enrollment.repository.EnrollmentRepository;
import com.backend.university.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentService {

    private final EnrollmentRepository repository;

    private final EnrollmentMapper mapper;

    private final StudentService studentService;

    private final CourseService courseService;

    @Transactional
    public EnrollmentOutputDTO create(EnrollmentInputDTO input) {
        this.validateExistsByNumber(input.getNumber());
        Enrollment enrollment = mapper.inputToEntity(input);
        repository.save(enrollment);
        return mapper.entityToOutput(enrollment);
    }

    @Transactional
    public EnrollmentOutputDTO update(EnrollmentUpdateDTO update) {
        Enrollment enrollment = this.findEntityById(update.getId());

        if (!update.getStudentCpf().equalsIgnoreCase(enrollment.getStudent().getCpf())) {
            enrollment.setStudent(studentService.findEntityByCfpWithoutEnrollment(update.getStudentCpf()));
        }
        if (!update.getCourse().equalsIgnoreCase(enrollment.getCourse().getName())) {
            enrollment.setCourse(courseService.findEntityByName(update.getCourse()));
        }
        enrollment.setNumber(update.getNumber());
        enrollment.setEnrollmentDate(update.getEnrollmentDate());
        repository.save(enrollment);
        return mapper.entityToOutput(enrollment);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

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

    private void validateExistsByNumber(int number) {
        if (repository.existsByNumber(number)) {
            throw new BusinessException(format("There is already an enrollment with number \"%s\".", number));
        }
    }

}
