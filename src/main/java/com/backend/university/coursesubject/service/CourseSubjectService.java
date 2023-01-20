package com.backend.university.coursesubject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseSubjectService {

    private final CourseSubjectRepository repository;

    public CourseSubject findEntityByCourseNameAndSubjectCode(String courseName, String subjectCode) {
        return repository.findByCourseNameAndSubjectCode(courseName, subjectCode)
                .orElseThrow(() -> new BusinessException(format("There no subject with code \"%s\" in the course \"%s\".", subjectCode, courseName)));
    }

    public CourseSubject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There no CourseSubject with id \"%s\".", id)));
    }

    public void validateExistsByCourseNameAndSubjectCode(String courseName, String subjectCode) {
        if (repository.existsByCourseNameAndSubjectCode(courseName, subjectCode)) {
            throw new BusinessException(format("There is already a subject with code \"%s\" in the course \"%s\".", subjectCode, courseName));
        }
    }

}
