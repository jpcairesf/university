package com.backend.university.student.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class StudentExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByEnrollment(int enrollmentNumber) {
        return () -> new EntityNotFoundException(StudentExceptionMessages.notFoundByEnrollment(enrollmentNumber));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(StudentExceptionMessages.notFoundById(id));
    }

}
