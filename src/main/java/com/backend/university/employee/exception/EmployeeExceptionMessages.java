package com.backend.university.employee.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class EmployeeExceptionMessages {

    private static final String NOT_FOUND_BY_ID = "There is no employee with ID \"%s\".";

    private static final String EXISTS_BY_CPF = "There is already an employee with CPF \"%s\".";

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByCpf(String cpf) {
        return format(EXISTS_BY_CPF, cpf);
    }

}
