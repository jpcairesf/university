package com.backend.university.common.validator;

import com.backend.university.subjectoffer.enumx.AmPmEnum;
import org.springframework.stereotype.Component;

import static com.backend.university.common.validator.exception.AmPmExceptionMessages.invalidAmPm;
import static com.backend.university.common.validator.exception.AmPmExceptionMessages.nullAmPm;

@Component
public class AmPmValidator implements ValueValidator<String> {

    @Override
    public void validate(String value) {
        try {
            AmPmEnum.valueOf(value);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(invalidAmPm(value));
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException(nullAmPm());
        }
    }
}
