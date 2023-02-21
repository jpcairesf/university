package com.backend.university.course.action;

import com.backend.university.course.domain.Course;
import com.backend.university.course.exception.CourseExceptionSupplier;
import com.backend.university.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CourseDeleteAction {

    private final CourseRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Course findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseExceptionSupplier.courseNotFoundById(id));
    }

}
