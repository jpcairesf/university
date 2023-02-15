package com.backend.university.course.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.CourseUpdateDTO;
import com.backend.university.course.dto.mapper.CourseMapper;
import com.backend.university.course.repository.CourseRepository;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

    private final CourseRepository repository;

    private final DepartmentService departmentService;

    @Transactional(readOnly = true)
    public CourseOutputDTO findById(Long id) {
        return CourseMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<CourseOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(CourseMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseOutputDTO create(CourseInputDTO input) {
        this.validateExistsByName(input.getName());

        Course course = new Course();
        course.setName(input.getName());
        course.setDepartment(departmentService.findEntityByName(input.getDepartment()));
        course.setCourseSubjects(new ArrayList<>());

        repository.save(course);
        return CourseMapper.entityToOutput(course);
    }

    @Transactional
    public CourseOutputDTO update(CourseUpdateDTO update) {
        Course course = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(course.getName())) {
            this.validateExistsByName(update.getName());
            course.setName(update.getName());
        }
        if (!update.getDepartment().equalsIgnoreCase(course.getDepartment().getName())) {
            course.setDepartment(departmentService.findEntityByName(update.getDepartment()));
        }
        repository.save(course);
        return CourseMapper.entityToOutput(course);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Course findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no course named \"%s\".", name)));
    }

    private Course findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no course with ID \"%s\".", id)));
    }

    private void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a course named \"%s\".", name));
        }
    }
}
