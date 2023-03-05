package com.backend.university.secretary.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class SecretaryExceptionMessages {

    private static final String NOT_FOUND_BY_ID = "There is no secretary with ID \"%s\".";

    private static final String EXISTS_BY_CPF = "There is already a secretary with CPF \"%s\".";

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByCpf(String cpf) {
        return format(EXISTS_BY_CPF, cpf);
    }

}
