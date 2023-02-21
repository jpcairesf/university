package com.backend.university.institute.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class InstituteExceptionSupplier {

    public static Supplier<EntityNotFoundException> instituteNotFoundByName(String name) {
        return () -> new EntityNotFoundException(InstituteExceptionMessages.instituteNotFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> instituteNotFoundById(Long id) {
        return () -> new EntityNotFoundException(InstituteExceptionMessages.instituteNotFoundById(id));
    }

}
