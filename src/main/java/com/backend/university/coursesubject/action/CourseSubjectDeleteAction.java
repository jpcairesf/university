package com.backend.university.coursesubject.action;

import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.exception.CourseSubjectExceptionSupplier;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseSubjectDeleteAction {

    private final CourseSubjectRepository repository;

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private CourseSubject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseSubjectExceptionSupplier.notFoundById(id));
    }

}
