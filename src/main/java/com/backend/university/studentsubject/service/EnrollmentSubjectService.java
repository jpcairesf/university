package com.backend.university.studentsubject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.student.service.EnrollmentService;
import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.EnrollmentSubjectInputDTO;
import com.backend.university.studentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.EnrollmentSubjectUpdateDTO;
import com.backend.university.studentsubject.dto.mapper.EnrollmentSubjectMapper;
import com.backend.university.studentsubject.repository.EnrollmentSubjectRepository;
import com.backend.university.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentSubjectService {

    private final EnrollmentSubjectRepository repository;

    private final EnrollmentService enrollmentService;

    private final SubjectService subjectService;

    @Transactional
    public EnrollmentSubjectOutputDTO findById(StudentSubjectId id) {
        return EnrollmentSubjectMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional
    public List<EnrollmentSubjectOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(EnrollmentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public EnrollmentSubjectOutputDTO create(EnrollmentSubjectInputDTO input) {
        this.validateExistsByEnrollmentNumberAndSubjectCodeAndSemester(
                input.getEnrollmentNumber(),
                input.getSubjectCode(),
                input.getSemester());

        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setId(new StudentSubjectId(
                enrollmentService.findIdByNumber(input.getEnrollmentNumber()),
                subjectService.findIdByCode(input.getSubjectCode())));
        studentSubject.setSemester(input.getSemester());

        repository.save(studentSubject);
        return EnrollmentSubjectMapper.entityToOutput(studentSubject);
    }

    @Transactional
    public EnrollmentSubjectOutputDTO update(EnrollmentSubjectUpdateDTO update) {
        StudentSubject studentSubject =
                this.findEntityByEnrollmentNumberAndSubjectCodeAndSemester(
                        update.getEnrollmentNumber(),
                        update.getSubjectCode(),
                        update.getSemester());
        studentSubject.setSemester(update.getSemester());
        studentSubject.setGrade(update.getGrade());

        repository.save(studentSubject);
        return EnrollmentSubjectMapper.entityToOutput(studentSubject);
    }

    @Transactional
    public void delete(StudentSubjectId id) {
        repository.delete(findEntityById(id));
    }

    public StudentSubject findEntityByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester) {
        return repository.findByEnrollmentNumberAndSubjectCodeAndSemester(number, code, semester)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\".", number, code, semester)));
    }

    public void validateExistsByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester) {
        if (repository.existsByEnrollmentNumberAndSubjectCodeAndSemester(number, code, semester)) {
            throw new BusinessException(format("There is already an enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\".", number, code, semester));
        }
    }

    private StudentSubject findEntityById(StudentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment subject with ID \"%s\".", id.toString())));
    }

}
