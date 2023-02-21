package com.backend.university.common.validator;

import com.backend.university.common.validator.exception.CPFExceptionMessages;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CPFValidator implements ValueValidator<String> {

    private static final Pattern pattern = Pattern.compile("\\d{3}.?\\d{3}.?\\d{3}-?\\d{2}");

    @Override
    public void validate(String value) {
        if (!pattern.matcher(value).matches()) {
            throw new IllegalArgumentException(CPFExceptionMessages.invalidCpfFormat(value));
        }
        else if (!this.validCpf(value)) {
            throw new IllegalArgumentException(CPFExceptionMessages.invalidCpfValue(value));
        }
    }

    private boolean validCpf(String value) {
        int[] intArray = this.getIntArray(value);

        // Check if the first and second digits before hyphen (array positions [9] and [10])
        // matches with calculated values for each one
        if (intArray[9] != this.getFirstDigit(intArray)) {
            return false;
        }
        if (intArray[10] != this.getSecondDigit(intArray)) {
            return false;
        }
        return true;
    }

    private int[] getIntArray(String value) {
        char[] valueArray = value.toCharArray();
        return new int[] {
                Character.getNumericValue(valueArray[0]),
                Character.getNumericValue(valueArray[1]),
                Character.getNumericValue(valueArray[2]),
                Character.getNumericValue(valueArray[4]),
                Character.getNumericValue(valueArray[5]),
                Character.getNumericValue(valueArray[6]),
                Character.getNumericValue(valueArray[8]),
                Character.getNumericValue(valueArray[9]),
                Character.getNumericValue(valueArray[10]),
                Character.getNumericValue(valueArray[12]),
                Character.getNumericValue(valueArray[13])
        };
    }

    private int getFirstDigit(int[] intArray) {
        int multiplier = 10;
        int total = 0;
        for (int i = 0; i < 9; i++) {
            total += multiplier * intArray[i];
            multiplier--;
        }

        int firstDigit = 0;
        int rest = total % 11;
        if (rest > 1) {
            firstDigit = 11 - rest;
        }

        return firstDigit;
    }

    private int getSecondDigit(int[] intArray) {
        int multiplier = 11;
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += multiplier * intArray[i];
            multiplier--;
        }

        int secondDigit = 0;
        int rest = total % 11;
        if (rest > 1) {
            secondDigit = 11 - rest;
        }

        return secondDigit;
    }

}
