package com.backend.university.common.validator.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class AmPmExceptionMessages {

    private static final String INVALID_AM_PM = "Invalid AM/PM value: \"%s\".";

    private static final String NULL_AM_PM = "Null AM/PM value.";

    public static String invalidAmPm(String value) {
        return format(INVALID_AM_PM, value);
    }

    public static String nullAmPm() {
        return NULL_AM_PM;
    }

}
