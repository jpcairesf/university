package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.exception.CourseExceptionSupplier;
import com.backend.university.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseRelatedAction {

    private final CourseRepository repository;

    public Course findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(CourseExceptionSupplier.notFoundByName(name));
    }

}
