package com.backend.university.department.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class DepartmentExceptionSupplier {

    public static Supplier<EntityNotFoundException> departmentNotFoundByName(String name) {
        return () -> new EntityNotFoundException(DepartmentExceptionMessages.departmentNotFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> departmentNotFoundById(Long id) {
        return () -> new EntityNotFoundException(DepartmentExceptionMessages.departmentNotFoundById(id));
    }

}
