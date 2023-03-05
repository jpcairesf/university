package com.backend.university.common.validator.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class CPFExceptionMessages {

    private static final String INVALID_CPF_FORMAT = "Invalid CPF format for value: \"%s\".";

    private static final String INVALID_CPF_VALUE = "Value of CPF \"%s\" is invalid.";

    public static String invalidCpfFormat(String value) {
        return format(INVALID_CPF_FORMAT, value);
    }

    public static String invalidCpfValue(String value) {
        return format(INVALID_CPF_VALUE, value);
    }

}
