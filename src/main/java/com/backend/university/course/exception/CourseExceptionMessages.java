package com.backend.university.course.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class CourseExceptionMessages {

    private static final String NOT_FOUND_BY_NAME = "There is no course named \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no course with ID \"%s\".";

    private static final String EXISTS_BY_NAME = "There is already a course named \"%s\".";

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
