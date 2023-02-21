package com.backend.university.coursesubject.exception;

import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class CourseSubjectExceptionMessages {

    private static final String COURSE_SUBJECT_NOT_FOUND_BY_ID = "There no CourseSubject with id \"%s\".";

    private static final String COURSE_SUBJECT_EXISTS_BY_COURSE_SUBJECT_CURRICULUM = "There is already a subject with code \"%s\" in course with name \"%s\" in the curriculum of year \"%s\".";

    public static String courseSubjectNotFoundById(Long id) {
        return format(COURSE_SUBJECT_NOT_FOUND_BY_ID, id);
    }

    public static String courseSubjectExistsByCourseSubjectCurriculum(String courseName, String subjectCode, int curriculumYear) {
        return format(COURSE_SUBJECT_EXISTS_BY_COURSE_SUBJECT_CURRICULUM, subjectCode, courseName, curriculumYear);
    }

}
