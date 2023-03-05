package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseUpdateDTO;
import com.backend.university.course.exception.CourseExceptionSupplier;
import com.backend.university.course.repository.CourseRepository;
import com.backend.university.department.action.DepartmentRelatedAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseUpdateAction {

    private final CourseRepository repository;

    private final DepartmentRelatedAction departmentRelatedAction;

    private final CourseValidatorAction validatorAction;

    public Course update(CourseUpdateDTO update) {
        Course course = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(course.getName())) {
            validatorAction.validateExistsByName(update.getName());
            course.setName(update.getName());
        }
        if (!update.getDepartment().equalsIgnoreCase(course.getDepartment().getName())) {
            course.setDepartment(departmentRelatedAction.findEntityByName(update.getDepartment()));
        }

        return repository.save(course);
    }

    private Course findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseExceptionSupplier.notFoundById(id));
    }

}
