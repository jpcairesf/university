package com.backend.university.dto.mapper;

import com.backend.university.domain.Course;
import com.backend.university.domain.CourseSubject;
import com.backend.university.domain.Department;
import com.backend.university.dto.input.CourseInputDTO;
import com.backend.university.dto.output.CourseOutputDTO;
import com.backend.university.dto.output.CourseSubjectOutputDTO;
import com.backend.university.dto.update.CourseSubjectUpdateDTO;
import com.backend.university.dto.update.CourseUpdateDTO;
import com.backend.university.repository.CourseRepository;
import com.backend.university.service.CourseService;
import com.backend.university.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseMapper {

    private final CourseSubjectMapper courseSubjectMapper;

    private final CourseService courseService;

    private final DepartmentService departmentService;

    public Course inputToEntity(CourseInputDTO input) {
        Course course = new Course();
        Department department = departmentService.findEntityByName(input.getDepartment());

        course.setName(input.getName());
        course.setDepartment(department);
        course.setCourseLoad(0);
        course.setCourseSubjects(new ArrayList<>());
        return course;
    }

    public Course updateToEntity(CourseUpdateDTO update, Course course) {
        if (!update.getName().equalsIgnoreCase(course.getName())) {
            course.setName(update.getName());
        }
        if (!update.getDepartment().equalsIgnoreCase(course.getDepartment().getName())) {
            course.setDepartment(departmentService.findEntityByName(update.getDepartment()));
        }
        if (!update.getCourseSubjects().isEmpty()) {
            deleteCourseSubjects(update.getCourseSubjects(), course);
            createCourseSubjects(update.getCourseSubjects(), course);
            updateCourseSubjects(update.getCourseSubjects(), course);
        }
        return course;
    }

    public CourseOutputDTO entityToOutput(Course course) {
        List<CourseSubjectOutputDTO> subjects = course.getCourseSubjects().stream()
                .map(courseSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return CourseOutputDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .department(course.getDepartment().getName())
                .courseSubjects(subjects)
                .courseLoad(course.getCourseLoad())
                .build();
    }

    private void createCourseSubjects(List<CourseSubjectUpdateDTO> subjectsUpdate, Course course) {
        List<CourseSubject> subjects = new ArrayList<>();

        subjectsUpdate.stream()
                .filter(subjectUpdate -> subjectUpdate.getId() == null)
                .forEach(subjectUpdate -> subjects.add(courseSubjectMapper.updateToEntity(subjectUpdate)));

        course.addCourseSubjects(subjects);
    }

    private void updateCourseSubjects(List<CourseSubjectUpdateDTO> subjectsUpdate, Course course) {
        course.getCourseSubjects().forEach(subject -> {
                Optional<CourseSubjectUpdateDTO> subjectUpdateOptional = subjectsUpdate.stream()
                    .filter(s -> s.getSubjectCode().equalsIgnoreCase(subject.getSubject().getCode()))
                    .findFirst();

                if (subjectUpdateOptional.isPresent()) {
                    CourseSubjectUpdateDTO subjectUpdate = subjectUpdateOptional.get();
                    subject.setSemester(subjectUpdate.getSemester());
                    subject.setRequired(subjectUpdate.isRequired());
                }
        });
    }

    private void deleteCourseSubjects(List<CourseSubjectUpdateDTO> subjectsUpdate, Course course) {
        course.getCourseSubjects().removeIf(subject -> !subjectsUpdate.stream()
                .map(CourseSubjectUpdateDTO::getId)
                .collect(Collectors.toList())
                .contains(subject.getId()));
    }

}
