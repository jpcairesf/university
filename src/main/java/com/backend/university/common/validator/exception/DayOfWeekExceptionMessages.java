package com.backend.university.common.validator.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class DayOfWeekExceptionMessages {

    private static final String INVALID_DAY_OF_WEEK = "Invalid Day Of Week value: \"%s\".";

    private static final String NULL_DAY_OF_WEEK = "Null Day Of Week value.";

    public static String invalidDayOfWeek(String value) {
        return format(INVALID_DAY_OF_WEEK, value);
    }

    public static String nullDayOfWeek() {
        return NULL_DAY_OF_WEEK;
    }

}
