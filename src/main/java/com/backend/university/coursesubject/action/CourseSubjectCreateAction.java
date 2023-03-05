package com.backend.university.coursesubject.action;

import com.backend.university.course.action.CourseRelatedAction;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.CourseSubjectInputDTO;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import com.backend.university.subject.action.SubjectRelatedAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseSubjectCreateAction {

    private final CourseSubjectRepository repository;

    private final SubjectRelatedAction subjectRelatedAction;

    private final CourseRelatedAction courseRelatedAction;

    private final CourseSubjectValidatorAction validatorAction;

    public CourseSubject create(CourseSubjectInputDTO input) {
        validatorAction.validateExistsByCourseNameAndSubjectCodeAndCurriculumYear(input.getCourse(), input.getSubjectCode(), input.getCurriculumYear());

        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setSubject(subjectRelatedAction.findEntityByCode(input.getSubjectCode()));
        courseSubject.setCourse(courseRelatedAction.findEntityByName(input.getCourse()));
        courseSubject.setRequired(input.isRequired());
        courseSubject.setCurriculumYear(input.getCurriculumYear());

        return repository.save(courseSubject);
    }

}
