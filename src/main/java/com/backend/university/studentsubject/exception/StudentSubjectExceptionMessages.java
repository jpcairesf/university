package com.backend.university.studentsubject.exception;

import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class StudentSubjectExceptionMessages {

    private static final String NOT_FOUND_BY_ID = "There is no enrollment subject with ID \"%s\".";

    private static final String NOT_FOUND_BY_ENROLLMENT_SUBJECT_SEMESTER_CLASS = "There is no enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\" in class with number \"%s\".";

    private static final String NO_AVAILABLE_VACANCIES = "There is no available vacancies.";

    private static final String EXISTS_BY_ENROLLMENT_SUBJECT_SEMESTER_CLASS = "There is already an enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\" in class with number \"%s\".";

    public static String notFoundById(StudentSubjectId id) {
        return format(NOT_FOUND_BY_ID, id);
    }

    public static String notFoundByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        return format(NOT_FOUND_BY_ENROLLMENT_SUBJECT_SEMESTER_CLASS, enrollmentNumber, subjectCode, semester, classNumber);
    }

    public static String noAvailableVacancies() {
        return NO_AVAILABLE_VACANCIES;
    }

    public static String existsByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        return format(EXISTS_BY_ENROLLMENT_SUBJECT_SEMESTER_CLASS, enrollmentNumber, subjectCode, semester, classNumber);
    }

}
