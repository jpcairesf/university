package com.backend.university.institute.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class InstituteExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByName(String name) {
        return () -> new EntityNotFoundException(InstituteExceptionMessages.notFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(InstituteExceptionMessages.notFoundById(id));
    }

}
