package com.backend.university.studentsubject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.studentsubject.exception.StudentSubjectExceptionMessages;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentSubjectValidatorAction {

    private final StudentSubjectRepository repository;

    public void validateVacanciesOffer(SubjectOffer subjectOffer) {
        if (repository.countBySubjectOffer(subjectOffer) == subjectOffer.getVacancies()) {
            throw new BusinessException(StudentSubjectExceptionMessages.noAvailableVacancies());
        }
    }

    public void validateExistsByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        if (repository.findEagerByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber).isPresent()) {
            throw new BusinessException(StudentSubjectExceptionMessages.existsByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber));
        }
    }

}
