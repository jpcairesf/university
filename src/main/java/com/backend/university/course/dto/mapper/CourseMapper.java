package com.backend.university.course.dto.mapper;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CourseMapper {

    public static CourseOutputDTO entityToOutput(Course course) {
        List<CourseSubjectOutputDTO> subjects =
                course.getCourseSubjects().stream()
                .map(CourseSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return CourseOutputDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .department(course.getDepartment().getName())
                .courseSubjects(subjects)
                .build();
    }

}
