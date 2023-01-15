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

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

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
        CourseSubject courseSubject = courseSubjectService.findEntityById(update.getId());
        if (!courseSubject.getSubject().getCode().equalsIgnoreCase(update.getSubjectCode())) {
            courseSubject.setSubject(subjectService.findEntityByCode(update.getSubjectCode()));
            }
        setIfNotNull(update.getRequired(), courseSubject::setRequired);
        setIfNotNull(update.getSemester(), courseSubject::setSemester);
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
