package com.backend.university.domain.enumx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Degree {

    GRADUATE("Graduate", 1),
    MASTER("Master", 2),
    PHD("PhD", 3);

    private final String description;

    private final int value;

    public static Degree toDegree(String description) {
        for (Degree d : values()) {
            if (d.description.equalsIgnoreCase(description)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Invalid Degree.");
    }

}
