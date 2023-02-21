package com.backend.university.professor.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class ProfessorExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByCpf(String cpf) {
        return () -> new EntityNotFoundException(ProfessorExceptionMessages.notFoundByCpf(cpf));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(ProfessorExceptionMessages.notFoundById(id));
    }

}
