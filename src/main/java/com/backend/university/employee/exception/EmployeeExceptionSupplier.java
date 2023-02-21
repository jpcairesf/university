package com.backend.university.employee.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class EmployeeExceptionSupplier {

    public static Supplier<EntityNotFoundException> employeeNotFoundById(Long id) {
        return () -> new EntityNotFoundException(EmployeeExceptionMessages.employeeNotFoundById(id));
    }

}
