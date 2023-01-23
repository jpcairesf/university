package com.backend.university.enrollmentsubject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.enrollmentsubject.domain.EnrollmentSubject;
import com.backend.university.enrollmentsubject.domain.id.EnrollmentSubjectId;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectInputDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectUpdateDTO;
import com.backend.university.enrollmentsubject.dto.mapper.EnrollmentSubjectMapper;
import com.backend.university.enrollmentsubject.repository.EnrollmentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentSubjectService {

    private final EnrollmentSubjectRepository repository;

    private final EnrollmentSubjectMapper mapper;

    @Transactional
    public EnrollmentSubjectOutputDTO create(EnrollmentSubjectInputDTO input) {
        this.validateExistsByEnrollmentNumberAndSubjectCodeAndSemester(
                input.getEnrollmentNumber(),
                input.getSubjectCode(),
                input.getSemester());
        EnrollmentSubject enrollmentSubject = mapper.inputToEntity(input);
        repository.save(enrollmentSubject);
        return mapper.entityToOutput(enrollmentSubject);
    }

    @Transactional
    public EnrollmentSubjectOutputDTO update(EnrollmentSubjectUpdateDTO update) {
        EnrollmentSubject enrollmentSubject =
                this.findEntityByEnrollmentNumberAndSubjectCodeAndSemester(
                        update.getEnrollmentNumber(),
                        update.getSubjectCode(),
                        update.getSemester());

        enrollmentSubject.setSemester(update.getSemester());
        enrollmentSubject.setGrade(update.getGrade());
        repository.save(enrollmentSubject);
        return mapper.entityToOutput(enrollmentSubject);
    }

    @Transactional
    public void delete(EnrollmentSubjectId id) {
        repository.delete(findEntityById(id));
    }

    public EnrollmentSubject findEntityByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester) {
        return repository.findByEnrollmentNumberAndSubjectCodeAndSemester(number, code, semester)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\".", number, code, semester)));
    }

    public void validateExistsByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester) {
        if (repository.existsByEnrollmentNumberAndSubjectCodeAndSemester(number, code, semester)) {
            throw new BusinessException(format("There is already an enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\".", number, code, semester));
        }
    }

    private EnrollmentSubject findEntityById(EnrollmentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no enrollment subject with ID \"%s\".", id.toString())));
    }

}
