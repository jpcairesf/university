package com.backend.university.coursesubject.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class CourseSubjectExceptionSupplier {

    public static Supplier<EntityNotFoundException> courseSubjectNotFoundById(Long id) {
        return () -> new EntityNotFoundException(CourseSubjectExceptionMessages.courseSubjectNotFoundById(id));
    }

}
