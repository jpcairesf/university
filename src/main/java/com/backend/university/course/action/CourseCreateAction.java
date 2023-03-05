package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.repository.CourseRepository;
import com.backend.university.department.action.DepartmentRelatedAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CourseCreateAction {

    private final CourseRepository repository;

    private final DepartmentRelatedAction departmentRelatedAction;

    private final CourseValidatorAction validatorAction;

    public Course create(CourseInputDTO input) {
        validatorAction.validateExistsByName(input.getName());

        Course course = new Course();
        course.setName(input.getName());
        course.setDepartment(departmentRelatedAction.findEntityByName(input.getDepartment()));
        course.setCourseSubjects(new ArrayList<>());

        return repository.save(course);
    }

}
