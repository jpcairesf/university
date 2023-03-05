package com.backend.university.subject.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class SubjectExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByCode(String code) {
        return () -> new EntityNotFoundException(SubjectExceptionMessages.notFoundByCode(code));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(SubjectExceptionMessages.notFoundById(id));
    }

}
