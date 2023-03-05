package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.exception.CourseExceptionSupplier;
import com.backend.university.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseGetAction {

    private final CourseRepository repository;

    public Course findById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseExceptionSupplier.notFoundById(id));
    }

    public List<Course> findAll() {
        return repository.findAll();
    }


}
