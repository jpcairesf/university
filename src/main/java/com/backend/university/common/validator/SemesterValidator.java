package com.backend.university.common.validator;

import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class SemesterValidator implements ValueValidator<Integer> {

    private static final String INVALID_YEAR = "Invalid year for value: \"%s\".";

    private static final String INVALID_SEMESTER = "Invalid semester for value: \"%s\".";

    @Override
    public void validate(Integer value) {
        int semester = value;
        int year = semester/10;

        if (year < 1900) {
            throw new IllegalArgumentException(format(INVALID_YEAR, value));
        }
        else if ((semester - year*10) != 1 && (semester - year*10) != 2) {
            throw new IllegalArgumentException(format(INVALID_SEMESTER, value));
        }
    }
}
