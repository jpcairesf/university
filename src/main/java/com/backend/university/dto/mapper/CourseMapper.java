package com.backend.university.dto.mapper;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Course;
import com.backend.university.domain.CourseSubject;
import com.backend.university.domain.Department;
import com.backend.university.dto.input.CourseInputDTO;
import com.backend.university.dto.output.CourseOutputDTO;
import com.backend.university.dto.update.CourseSubjectUpdateDTO;
import com.backend.university.dto.update.CourseUpdateDTO;
import com.backend.university.repository.CourseRepository;
import com.backend.university.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseMapper {

    private final CourseRepository repository;

    private final CourseSubjectMapper courseSubjectMapper;

    private final DepartmentService departmentService;

    public Course inputToEntity(CourseInputDTO input) {
        // Service
        if (repository.existsByName(input.getName())) {
            throw new BusinessException(format("There is already a course named \"%s\".", input.getName()));
        }

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
            // Service
            if (repository.existsByName(update.getName())) {
                throw new BusinessException(format("There is already a course named \"%s\".", update.getName()));
            }
            course.setName(update.getName());
        }
        if (!update.getDepartment().equalsIgnoreCase(course.getDepartment().getName())) {
            course.setDepartment(departmentService.findEntityByName(update.getDepartment()));
        }
        if (!update.getCourseSubjects().isEmpty()) {
            deleteCourseSubjects(update.getCourseSubjects(), course);
            createCourseSubjects(update, course);
            updateCourseSubjects(update, course);
        }
        return course;
    }

    public CourseOutputDTO entityToOutput(Course course) {
        return CourseOutputDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .department(course.getDepartment().getName())
                .courseSubjects(courseSubjectMapper.entityToOutput(course.getCourseSubjects()))
                .courseLoad(course.getCourseLoad())
                .build();
    }

    private void createCourseSubjects(CourseUpdateDTO update, Course course) {
        List<CourseSubject> subjectsUpdate = new ArrayList<>();

        course.getCourseSubjects().forEach(subject -> update
                .getCourseSubjects().stream()
                .filter(subjectUpdate -> subjectUpdate.getId() == null)
                .forEach(subjectUpdate -> subjectsUpdate.add(courseSubjectMapper.updateToEntity(subjectUpdate))));

        course.addCourseSubjects(subjectsUpdate);
    }

    private void updateCourseSubjects(CourseUpdateDTO update, Course course) {
        course.getCourseSubjects().forEach(subject -> {
                Optional<CourseSubjectUpdateDTO> subjectUpdateOptional = update
                    .getCourseSubjects().stream()
                    .filter(s -> s.getSubjectCode().equalsIgnoreCase(subject.getSubject().getCode()))
                    .findFirst();

                if (subjectUpdateOptional.isPresent()) {
                    CourseSubjectUpdateDTO subjectUpdate = subjectUpdateOptional.get();
                    subject.setSemester(subjectUpdate.getSemester());
                    subject.setRequired(subjectUpdate.isRequired());
                }
        });
    }

    private void deleteCourseSubjects(List<CourseSubjectUpdateDTO> update, Course course) {
        course.getCourseSubjects().removeIf(subject -> !update.stream()
                .map(CourseSubjectUpdateDTO::getId)
                .collect(Collectors.toList())
                .contains(subject.getId()));
    }

    private List<CourseSubjectUpdateDTO> toCourseSubjectUpdateDto(List<CourseSubject> courseSubjects) {
        return courseSubjects.stream().map(s -> CourseSubjectUpdateDTO.builder()
                .id(s.getId())
                .course(s.getCourse().getName())
                .subjectCode(s.getSubject().getCode())
                .required(s.isRequired())
                .semester(s.getSemester())
                .build())
                .collect(Collectors.toList());
    }

}
