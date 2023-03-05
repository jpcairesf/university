package com.backend.university.subjectoffer.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class SubjectOfferExceptionMessages {

    private static final String NOT_FOUND_BY_COURSE_SUBJECT_SEMESTER_CLASS = "There is no subject offer for course with ID \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".";

    private static final String NOT_FOUND_BY_ID = "There is no subject offer with ID \"%s\".";

    private static final String EXISTS_BY_COURSE_SUBJECT_SEMESTER_CLASS = "There is already a subject offer for offer for subject course with name \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".";

    public static String notFoundByCourseSubjectSemesterClass(Long courseId, String subjectCode, int semester, int classNumber) {
        return format(NOT_FOUND_BY_COURSE_SUBJECT_SEMESTER_CLASS, courseId, subjectCode, semester, classNumber);
    }

    public static String notFoundById(Long id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String existsByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber) {
        return format(EXISTS_BY_COURSE_SUBJECT_SEMESTER_CLASS, courseName, subjectCode, semester, classNumber);
    }

}
