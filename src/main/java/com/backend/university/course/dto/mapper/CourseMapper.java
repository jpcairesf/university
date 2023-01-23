package com.backend.university.course.dto.mapper;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import com.backend.university.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseMapper {

    private final CourseSubjectMapper courseSubjectMapper;

    private final DepartmentService departmentService;

    //TODO Refactor inputToEntity method to services
    public Course inputToEntity(CourseInputDTO input) {
        Course course = new Course();
        course.setName(input.getName());
        course.setDepartment(departmentService.findEntityByName(input.getDepartment()));
        course.setCourseLoad(0);
        course.setCourseSubjects(new LinkedHashSet<>());
        return course;
    }

    //TODO Make entityToOutput method static
    public CourseOutputDTO entityToOutput(Course course) {
        Set<CourseSubjectOutputDTO> subjects =
                course.getCourseSubjects().stream()
                .map(courseSubjectMapper::entityToOutput)
                .collect(Collectors.toSet());

        return CourseOutputDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .department(course.getDepartment().getName())
                .courseSubjects(subjects)
                .courseLoad(course.getCourseLoad())
                .build();
    }

}
