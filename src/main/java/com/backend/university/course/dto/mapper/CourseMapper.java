package com.backend.university.course.dto.mapper;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.course.dto.CourseUpdateDTO;
import com.backend.university.course.service.CourseService;
import com.backend.university.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseMapper {

    private final CourseSubjectMapper courseSubjectMapper;

    private final CourseService courseService;

    private final DepartmentService departmentService;

    public Course inputToEntity(CourseInputDTO input) {
        Course course = new Course();
        course.setName(input.getName());
        course.setDepartment(departmentService.findEntityByName(input.getDepartment()));
        course.setCourseLoad(0);
        course.setCourseSubjects(new LinkedHashSet<>());
        return course;
    }

    public Course updateToEntity(CourseUpdateDTO update) {
        Course course = courseService.findEntityById(update.getId());
        if (!course.getDepartment().getName().equalsIgnoreCase(update.getDepartment())) {
            course.setDepartment(departmentService.findEntityByName(update.getDepartment()));
        }
        setIfNotNull(update.getName(), course::setName);
        return course;
    }

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
