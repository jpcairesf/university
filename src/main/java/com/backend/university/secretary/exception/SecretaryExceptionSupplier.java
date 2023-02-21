package com.backend.university.secretary.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class SecretaryExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(SecretaryExceptionMessages.notFoundById(id));
    }

}
