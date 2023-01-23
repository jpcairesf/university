package com.backend.university.coursesubject.dto.mapper;

import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.CourseSubjectInputDTO;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.coursesubject.service.CourseSubjectService;
import com.backend.university.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseSubjectMapper {

    private final SubjectService subjectService;

    private final CourseSubjectService courseSubjectService;

    public CourseSubject inputToEntity(CourseSubjectInputDTO input) {
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setSubject(subjectService.findEntityByCode(input.getSubjectCode()));
        courseSubject.setSemester(input.getSemester());
        courseSubject.setRequired(input.isRequired());
        return courseSubject;
    }

    public CourseSubjectOutputDTO entityToOutput(CourseSubject courseSubject) {
        return CourseSubjectOutputDTO.builder()
                .id(courseSubject.getId())
                .course(courseSubject.getCourse().getName())
                .subjectCode(courseSubject.getSubject().getCode())
                .semester(courseSubject.getSemester())
                .required(courseSubject.isRequired())
                .build();
    }
}
