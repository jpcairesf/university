package com.backend.university.professor.domain.enumx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank {

    EMERITUS("Professor Emeritus", Degree.PHD),
    FULL("Full Professor", Degree.PHD),
    ASSOCIATE("Associate Professor", Degree.PHD),
    ASSISTANT("Assistant Professor", Degree.PHD),
    LECTURER("Lecturer", Degree.MASTER),
    ASSISTANT_LECTURER("Assistant Lecturer", Degree.GRADUATE),
    TEMPORARY_LECTURER("Temporary Lecturer", Degree.GRADUATE);

    private final String description;

    private final Degree degreeRequirement;

    public static Rank toRank(String description) {
        for (Rank r : values()) {
            if (r.description.equalsIgnoreCase(description)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid Rank.");
    }

}
