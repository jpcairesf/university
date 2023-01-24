package com.backend.university.coursesubject.dto.mapper;

import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseSubjectMapper {

    public static CourseSubjectOutputDTO entityToOutput(CourseSubject courseSubject) {
        return CourseSubjectOutputDTO.builder()
                .id(courseSubject.getId())
                .course(courseSubject.getCourse().getName())
                .subjectCode(courseSubject.getSubject().getCode())
                .semester(courseSubject.getSemester())
                .required(courseSubject.isRequired())
                .build();
    }
}
