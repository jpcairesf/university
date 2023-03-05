package com.backend.university.coursesubject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.coursesubject.exception.CourseSubjectExceptionMessages;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseSubjectValidatorAction {

    private final CourseSubjectRepository repository;

    public void validateExistsByCourseNameAndSubjectCodeAndCurriculumYear(String courseName, String subjectCode, int curriculumYear) {
        if (repository.findByCourseNameAndSubjectCodeAndCurriculumYear(courseName, subjectCode, curriculumYear).isPresent()) {
            throw new BusinessException(CourseSubjectExceptionMessages.existsByCourseNameAndSubjectCodeAndCurriculumYear(subjectCode, courseName, curriculumYear));
        }
    }

}
