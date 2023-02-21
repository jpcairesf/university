package com.backend.university.room.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class RoomExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByName(String name) {
        return () -> new EntityNotFoundException(RoomExceptionMessages.notFoundByName(name));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(RoomExceptionMessages.notFoundById(id));
    }

}
