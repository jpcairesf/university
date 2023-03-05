package com.backend.university.department.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class DepartmentExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByName(String name) {
        return () -> new EntityNotFoundException(DepartmentExceptionMessages.notFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(DepartmentExceptionMessages.notFoundById(id));
    }

}
