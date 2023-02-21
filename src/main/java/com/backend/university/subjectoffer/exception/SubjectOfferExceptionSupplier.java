package com.backend.university.subjectoffer.exception;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class SubjectOfferExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundByCourseSubjectSemesterClass(Long courseId, String subjectCode, int semester, int classNumber) {
        return () -> new EntityNotFoundException(SubjectOfferExceptionMessages.notFoundByCourseSubjectSemesterClass(courseId, subjectCode, semester, classNumber));
    }

    public static Supplier<EntityNotFoundException> notFoundById(Long id) {
        return () -> new EntityNotFoundException(SubjectOfferExceptionMessages.notFoundById(id));
    }

}
