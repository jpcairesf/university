package com.backend.university.department.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class DepartmentExceptionMessages {

    private static final String NOT_FOUND_BY_NAME = "There is no department named \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no department with ID \"%s\".";

    private static final String EXISTS_BY_NAME = "There is already a department named \"%s\".";

    public static String notFoundByName(String name) {
        return format(NOT_FOUND_BY_NAME, name);
    }

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByName(String name) {
        return format(EXISTS_BY_NAME, name);
    }

}
