package com.backend.university.coursesubject.action;

import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.exception.CourseSubjectExceptionSupplier;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseSubjectGetAction {

    private final CourseSubjectRepository repository;

    public CourseSubject findById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseSubjectExceptionSupplier.notFoundById(id));
    }

    public List<CourseSubject> findAll() {
        return repository.findAll();
    }

}
