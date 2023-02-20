package com.backend.university.coursesubject.action;

import com.backend.university.course.service.CourseService;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.CourseSubjectInputDTO;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import com.backend.university.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CourseSubjectCreateAction {

    private final CourseSubjectRepository repository;

    private final SubjectService subjectService;

    private final CourseService courseService;

    private final CourseSubjectValidatorAction validatorAction;

    @Transactional
    public CourseSubjectOutputDTO create(CourseSubjectInputDTO input) {
        this.validatorAction.validateExistsByCourseNameAndSubjectCodeAndCurriculumYear(input.getCourse(), input.getSubjectCode(), input.getCurriculumYear());

        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setSubject(subjectService.findEntityByCode(input.getSubjectCode()));
        courseSubject.setCourse(courseService.findEntityByName(input.getCourse()));
        courseSubject.setRequired(input.isRequired());
        courseSubject.setCurriculumYear(input.getCurriculumYear());

        repository.save(courseSubject);
        return CourseSubjectMapper.entityToOutput(courseSubject);
    }

}
