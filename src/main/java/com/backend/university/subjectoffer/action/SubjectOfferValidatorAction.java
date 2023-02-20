package com.backend.university.subjectoffer.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.common.validator.AmPmValidator;
import com.backend.university.common.validator.DayOfWeekValidator;
import com.backend.university.common.validator.SemesterValidator;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SubjectOfferValidatorAction {

    private final SubjectOfferRepository repository;

    private final DayOfWeekValidator dayOfWeekValidator;

    private final AmPmValidator amPmValidator;

    private final SemesterValidator semesterValidator;

    public void validateAmPm(String amPm) {
        this.amPmValidator.validate(amPm);
    }

    public void validateDayOfWeek(String dayOfWeek) {
        this.dayOfWeekValidator.validate(dayOfWeek);
    }

    public void validateSemester(int semester) {
        this.semesterValidator.validate(semester);
    }

    public void validateExistsByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber) {
        if (repository.existsByCourseNameAndSubjectCodeAndSemesterAndClassNumber(courseName, subjectCode, semester, classNumber)) {
            throw new BusinessException(format("There is already a subject offer for offer for subject course with name \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".", courseName, subjectCode, semester, classNumber));
        }
    }

}
