package com.backend.university.department.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class DepartmentExceptionMessages {

    private static final String DEPARTMENT_NOT_FOUND_BY_NAME = "There is no department named \"%s\".";

    private static final String DEPARTMENT_NOT_FOUND_BY_ID = "There is no department with ID \"%s\".";

    private static final String DEPARTMENT_EXISTS_BY_NAME = "There is already a department named \"%s\".";

    public static String departmentNotFoundByName(String name) {
        return format(DEPARTMENT_NOT_FOUND_BY_NAME, name);
    }

    public static String departmentNotFoundById(Long id) {
        return format(DEPARTMENT_NOT_FOUND_BY_ID, id);
    }

    public static String departmentExistsByName(String name) {
        return format(DEPARTMENT_EXISTS_BY_NAME, name);
    }

}
