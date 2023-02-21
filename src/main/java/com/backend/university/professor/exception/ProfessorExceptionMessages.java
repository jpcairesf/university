package com.backend.university.professor.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class ProfessorExceptionMessages {

    private static final String NOT_FOUND_BY_CPF = "There is no professor with CPF \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no professor with ID \"%s\".";

    private static final String EXISTS_BY_CPF = "There already a professor with CPF \"%s\".";

    public static String notFoundByCpf(String cpf) {
        return format(NOT_FOUND_BY_CPF, cpf);
    }

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByCpf(String cpf) {
        return format(EXISTS_BY_CPF, cpf);
    }

}
