package com.backend.university.common.validator;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

import static com.backend.university.common.validator.exception.DayOfWeekExceptionMessages.invalidDayOfWeek;
import static com.backend.university.common.validator.exception.DayOfWeekExceptionMessages.nullDayOfWeek;

@Component
public class DayOfWeekValidator implements ValueValidator<String> {

    @Override
    public void validate(String value) {
        try {
            DayOfWeek.valueOf(value);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(invalidDayOfWeek(value));
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException(nullDayOfWeek());
        }
    }
}
