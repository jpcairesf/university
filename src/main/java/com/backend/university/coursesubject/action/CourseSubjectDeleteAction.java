package com.backend.university.coursesubject.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class CourseSubjectDeleteAction {

    private final CourseSubjectRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private CourseSubject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There no CourseSubject with id \"%s\".", id)));
    }

}
