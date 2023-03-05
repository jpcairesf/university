package com.backend.university.subjectoffer.enumx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmPmEnum {

    AM("AM"),
    PM("PM");

    private final String description;

}
