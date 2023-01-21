package com.backend.university.course.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.CourseUpdateDTO;
import com.backend.university.course.dto.mapper.CourseMapper;
import com.backend.university.course.repository.CourseRepository;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import com.backend.university.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

    private final CourseRepository repository;

    private final CourseMapper mapper;

    private final DepartmentService departmentService;

    @Transactional
    public CourseOutputDTO create(CourseInputDTO input) {
        this.validateExistsByName(input.getName());
        Course course = mapper.inputToEntity(input);
        repository.save(course);
        return mapper.entityToOutput(course);
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
        return mapper.entityToOutput(course);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    @Transactional
    public void addSubject(CourseSubject subject) {
        Course course = subject.getCourse();
        course.addCourseSubject(subject);
        repository.save(course);
    }

    @Transactional(readOnly = true)
    public CourseOutputDTO getById(Long id) {
        return mapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public CourseOutputDTO getByName(String name) {
        return mapper.entityToOutput(this.findEntityByName(name));
    }

    @Transactional(readOnly = true)
    public List<CourseOutputDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToOutput)
                .collect(Collectors.toList());
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
