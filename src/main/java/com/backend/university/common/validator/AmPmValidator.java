package com.backend.university.common.validator;

import com.backend.university.subjectoffer.enumx.AmPmEnum;

import static java.lang.String.format;

public class AmPmValidator implements ValueValidator<String> {

    private static final String INVALID_AM_PM = "Invalid AM/PM value: \"%s\".";
    private static final String NULL_AM_PM = "Null AM/PM value.";

    @Override
    public void validate(String value) {
        try {
            AmPmEnum.valueOf(value);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(format(INVALID_AM_PM, value));
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException(NULL_AM_PM);
        }
    }
}
