package com.backend.university.coursesubject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class CourseSubjectValidatorAction {

    private final CourseSubjectRepository repository;

    public void validateExistsByCourseNameAndSubjectCodeAndCurriculumYear(String courseName, String subjectCode, int curriculumYear) {
        if (repository.findByCourseNameAndSubjectCodeAndCurriculumYear(courseName, subjectCode, curriculumYear).isPresent()) {
            throw new BusinessException(format("There is already a subject with code \"%s\" in course with name \"%s\" in the curriculum of year \"%s\".", subjectCode, courseName, curriculumYear));
        }
    }

}
