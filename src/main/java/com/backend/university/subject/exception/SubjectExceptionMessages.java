package com.backend.university.subject.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class SubjectExceptionMessages {

    private static final String NOT_FOUND_BY_CODE = "There is no subject with code \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no subject with ID \"%s\".";

    private static final String EXISTS_BY_CODE = "There is already a subject with code \"%s\".";

    public static String notFoundByCode(String code) {
        return format(NOT_FOUND_BY_CODE, code);
    }

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByCode(String code) {
        return format(EXISTS_BY_CODE, code);
    }

}
