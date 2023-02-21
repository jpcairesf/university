package com.backend.university.institute.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class InstituteExceptionMessages {

    private static final String NOT_FOUND_BY_NAME = "There is no institute named \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no institute with ID \"%s\".";

    private static final String EXISTS_BY_NAME = "There is already an institute named \"%s\".";

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
