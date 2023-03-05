package com.backend.university.room.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class RoomExceptionMessages {

    private static final String NOT_FOUND_BY_NAME = "There is no room named \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no room with ID \"%s\".";

    private static final String EXISTS_BY_NAME = "There is already a room named \"%s\".";

    public static String notFoundByName(String name) {
        return format(NOT_FOUND_BY_NAME, name);
    }

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByName(String name) {
        return format(EXISTS_BY_NAME, name);
    }

}
