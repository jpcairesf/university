package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Course;
import com.backend.university.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

    private final CourseRepository repository;

    public Course findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no course named \"%s\".", name)));
    }

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a course named \"%s\".", name));
        }
    }

    public Course findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no course with ID \"%s\".", id)));
    }
}
