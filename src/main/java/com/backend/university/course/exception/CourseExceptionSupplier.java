package com.backend.university.course.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class CourseExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByName(String name) {
        return () -> new EntityNotFoundException(CourseExceptionMessages.notFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(CourseExceptionMessages.notFoundById(id));
    }

}
