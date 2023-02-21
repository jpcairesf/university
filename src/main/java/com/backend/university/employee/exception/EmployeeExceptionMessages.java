package com.backend.university.employee.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class EmployeeExceptionMessages {

    private static final String EMPLOYEE_NOT_FOUND_BY_ID = "There is no employee with ID \"%s\".";

    private static final String EMPLOYEE_EXISTS_BY_CPF = "There is already an employee with CPF \"%s\".";

    public static String employeeNotFoundById(Long id) {
        return format(EMPLOYEE_NOT_FOUND_BY_ID, id);
    }

    public static String employeeExistsByCpf(String cpf) {
        return format(EMPLOYEE_EXISTS_BY_CPF, cpf);
    }

}
