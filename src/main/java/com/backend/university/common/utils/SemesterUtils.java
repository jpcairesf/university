package com.backend.university.common.utils;

public class SemesterUtils {

    private SemesterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getDescription(int semester) {
        int year = semester/10;
        if (year > 3000 || year < 2000) {
            throw new IllegalArgumentException("Invalid year.");
        }
        else if ((semester - year*10) != 1 && (semester - year*10) != 2) {
            throw new IllegalArgumentException("Invalid semester.");
        }
        return year + "." + (semester - year*10);
    }

    public static int toSemester(String description) {
        try {
            String[] semesterArray = description.split("\\.");
            return Integer.parseInt(semesterArray[0]) * 10 + Integer.parseInt(semesterArray[1]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid semester description.");
        }
    }

}
