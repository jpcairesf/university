package com.backend.university.course.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class CourseExceptionMessages {

    private static final String COURSE_NOT_FOUND_BY_NAME = "There is no course named \"%s\".";

    private static final String COURSE_NOT_FOUND_BY_ID = "There is no course with ID \"%s\".";

    private static final String COURSE_EXISTS_BY_NAME = "There is already a course named \"%s\".";

    public static String courseNotFoundByName(String name) {
        return format(COURSE_NOT_FOUND_BY_NAME, name);
    }

    public static String courseNotFoundById(Long id) {
        return format(COURSE_NOT_FOUND_BY_ID, id);
    }

    public static String courseExistsByName(String name) {
        return format(COURSE_EXISTS_BY_NAME, name);
    }

}
