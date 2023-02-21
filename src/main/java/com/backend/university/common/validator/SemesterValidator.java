package com.backend.university.common.validator;

import org.springframework.stereotype.Component;

import static com.backend.university.common.validator.exception.SemesterExceptionMessages.invalidSemester;
import static com.backend.university.common.validator.exception.SemesterExceptionMessages.invalidYear;

@Component
public class SemesterValidator implements ValueValidator<Integer> {

    @Override
    public void validate(Integer value) {
        int semester = value;
        int year = semester/10;

        if (year < 1900) {
            throw new IllegalArgumentException(invalidYear(value));
        }
        else if ((semester - year*10) != 1 && (semester - year*10) != 2) {
            throw new IllegalArgumentException(invalidSemester(value));
        }
    }
}
