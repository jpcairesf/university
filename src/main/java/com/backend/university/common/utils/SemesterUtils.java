package com.backend.university.common.utils;

public class SemesterUtils {

    private SemesterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getDescription(int semester) {
        int year = semester/10;
        return year + "." + (semester - year);
    }

    public static int toSemester(String description) {
        String[] semesterArray = description.split("\\.");
        return Integer.parseInt(semesterArray[0]) * 10 + Integer.parseInt(semesterArray[1]);
    }

}
