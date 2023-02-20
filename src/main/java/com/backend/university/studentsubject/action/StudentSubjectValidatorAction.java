package com.backend.university.studentsubject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class StudentSubjectValidatorAction {

    private final StudentSubjectRepository repository;

    public void validateVacanciesOffer(SubjectOffer subjectOffer) {
        if (repository.countBySubjectOffer(subjectOffer) == subjectOffer.getVacancies()) {
            throw new BusinessException("There is no available vacancies");
        }
    }

    public void validateExistsByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        if (repository.findEagerByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber).isPresent()) {
            throw new BusinessException(format("There is already an enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\" in class with number \"%s\".", enrollmentNumber, subjectCode, semester, classNumber));
        }
    }

}
