package com.backend.university.domain.enumx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectSchedule {

    AM_7H00_8H50("7h00 - 8h50 - AM"),
    AM_8H50_10H40("8h50 - 10h40 - AM"),
    AM_10H40_12H30("10h40 - 12h30 - AM"),
    PM_1H00_2H50("1h00 - 2h50 - PM"),
    PM_2H50_4H40("2h50 - 4h40 - PM"),
    PM_4H40_6H30("4h40 - 6h30 - PM"),
    PM_6H30_8H20("6h30 - 8h20 - PM"),
    PM_8H20_10H10("8h20 - 10h10 - PM");

    private final String description;

    public static SubjectSchedule toSchedule(String description) {
        for (SubjectSchedule s : values()) {
            if (s.description.equalsIgnoreCase(description)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid SubjectSchedule.");
    }

}
