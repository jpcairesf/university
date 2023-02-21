package com.backend.university.institute.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class InstituteExceptionMessages {

    private static final String INSTITUTE_NOT_FOUND_BY_NAME = "There is no institute named \"%s\".";

    private static final String INSTITUTE_NOT_FOUND_BY_ID = "There is no institute with ID \"%s\".";

    private static final String INSTITUTE_EXISTS_BY_NAME = "There is already an institute named \"%s\".";

    public static String instituteNotFoundByName(String name) {
        return format(INSTITUTE_NOT_FOUND_BY_NAME, name);
    }

    public static String instituteNotFoundById(Long id) {
        return format(INSTITUTE_NOT_FOUND_BY_ID, id);
    }

    public static String instituteExistsByName(String name) {
        return format(INSTITUTE_EXISTS_BY_NAME, name);
    }

}
