package com.backend.university.course.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class CourseExceptionSupplier {

    public static Supplier<EntityNotFoundException> courseNotFoundByName(String name) {
        return () -> new EntityNotFoundException(CourseExceptionMessages.courseNotFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> courseNotFoundById(Long id) {
        return () -> new EntityNotFoundException(CourseExceptionMessages.courseNotFoundById(id));
    }

}
