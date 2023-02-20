package com.backend.university.course.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class CourseValidatorAction {

    private final CourseRepository repository;

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a course named \"%s\".", name));
        }
    }

}
