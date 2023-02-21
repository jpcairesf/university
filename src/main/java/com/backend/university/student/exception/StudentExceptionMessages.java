package com.backend.university.student.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class StudentExceptionMessages {

    private static final String NOT_FOUND_BY_ENROLLMENT = "There is no student with enrollment number \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no student with ID \"%s\".";

    private static final String EXISTS_BY_ENROLLMENT = "There is already an student with number \"%s\".";

    private static final String EXISTS_BY_CPF = "There is already an student with CPF \"%s\".";

    public static String notFoundByEnrollment(int enrollmentNumber) {
        return format(NOT_FOUND_BY_ENROLLMENT, enrollmentNumber);
    }

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByEnrollment(int enrollmentNumber) {
        return format(EXISTS_BY_ENROLLMENT, enrollmentNumber);
    }

    public static String existsByCpf(String cpf) {
        return format(EXISTS_BY_CPF, cpf);
    }

}
