package com.backend.university.common.validator.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class SemesterExceptionMessages {

    private static final String INVALID_YEAR = "Invalid year for value: \"%s\".";

    private static final String INVALID_SEMESTER = "Invalid semester for value: \"%s\".";

    public static String invalidYear(int value) {
        return format(INVALID_YEAR, value);
    }

    public static String invalidSemester(int value) {
        return format(INVALID_SEMESTER, value);
    }

}
