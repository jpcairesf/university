package com.backend.university.studentsubject.exception;

import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@UtilityClass
public class StudentSubjectExceptionSupplier {

    public static Supplier<EntityNotFoundException> notFoundById(StudentSubjectId id) {
        return () -> new EntityNotFoundException(StudentSubjectExceptionMessages.notFoundById(id));
    }

    public static Supplier<EntityNotFoundException> notFoundByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        return () -> new EntityNotFoundException(StudentSubjectExceptionMessages.notFoundByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber));
    }

}
