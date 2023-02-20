package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.mapper.CourseMapper;
import com.backend.university.course.repository.CourseRepository;
import com.backend.university.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CourseCreateAction {

    private final CourseRepository repository;

    private final DepartmentService departmentService;

    private final CourseValidatorAction validatorAction;

    public CourseOutputDTO create(CourseInputDTO input) {
        this.validatorAction.validateExistsByName(input.getName());

        Course course = new Course();
        course.setName(input.getName());
        course.setDepartment(departmentService.findEntityByName(input.getDepartment()));
        course.setCourseSubjects(new ArrayList<>());

        repository.save(course);
        return CourseMapper.entityToOutput(course);
    }

}
