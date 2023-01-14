package com.backend.university.dto.mapper;

import com.backend.university.domain.CourseSubject;
import com.backend.university.dto.input.CourseSubjectInputDTO;
import com.backend.university.dto.output.CourseSubjectOutputDTO;
import com.backend.university.dto.update.CourseSubjectUpdateDTO;
import com.backend.university.service.CourseService;
import com.backend.university.service.CourseSubjectService;
import com.backend.university.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseSubjectMapper {

    private final CourseService courseService;

    private final SubjectService subjectService;

    private final CourseSubjectService courseSubjectService;

    public CourseSubject inputToEntity(CourseSubjectInputDTO input) {
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourse(courseService.findEntityByName(input.getCourse()));
        courseSubject.setSubject(subjectService.findEntityByCode(input.getSubjectCode()));
        courseSubject.setSemester(input.getSemester());
        courseSubject.setRequired(input.isRequired());
        return courseSubject;
    }

    public CourseSubject updateToEntity(CourseSubjectUpdateDTO update) {
        CourseSubject courseSubject;

        if (update.getId() == null) {
            courseSubject = new CourseSubject();
            courseSubject.setCourse(courseService.findEntityByName(update.getCourse()));
            courseSubject.setSubject(subjectService.findEntityByCode(update.getSubjectCode()));
        }
        else {
            courseSubject = courseSubjectService.findEntityById(update.getId());
            if (!courseSubject.getSubject().getCode().equalsIgnoreCase(update.getSubjectCode())) {
                courseSubject.setSubject(subjectService.findEntityByCode(update.getSubjectCode()));
            }
        }
        courseSubject.setRequired(update.isRequired());
        courseSubject.setSemester(update.getSemester());
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
