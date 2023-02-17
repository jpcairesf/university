package com.backend.university.common.validator;

import java.time.DayOfWeek;

import static java.lang.String.format;

public class DayOfWeekValidator implements ValueValidator<String> {

    private static final String INVALID_DAY_OF_WEEK = "Invalid Day Of Week value: \"%s\".";
    private static final String NULL_DAY_OF_WEEK = "Null Day Of Week value.";

    @Override
    public void validate(String value) {
        try {
            DayOfWeek.valueOf(value);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(format(INVALID_DAY_OF_WEEK, value));
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException(NULL_DAY_OF_WEEK);
        }
    }
}
