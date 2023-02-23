package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.CourseUpdateDTO;
import com.backend.university.course.dto.mapper.CourseMapper;
import com.backend.university.course.exception.CourseExceptionSupplier;
import com.backend.university.course.repository.CourseRepository;
import com.backend.university.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CourseUpdateAction {

    private final CourseRepository repository;

    private final DepartmentService departmentService;

    private final CourseValidatorAction validatorAction;

    @Transactional
    public CourseOutputDTO update(CourseUpdateDTO update) {
        Course course = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(course.getName())) {
            validatorAction.validateExistsByName(update.getName());
            course.setName(update.getName());
        }
        if (!update.getDepartment().equalsIgnoreCase(course.getDepartment().getName())) {
            course.setDepartment(departmentService.findEntityByName(update.getDepartment()));
        }

        repository.save(course);
        return CourseMapper.entityToOutput(course);
    }

    private Course findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseExceptionSupplier.notFoundById(id));
    }

}
