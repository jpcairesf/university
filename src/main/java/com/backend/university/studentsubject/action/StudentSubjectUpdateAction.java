package com.backend.university.studentsubject.action;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.dto.StudentSubjectUpdateDTO;
import com.backend.university.studentsubject.exception.StudentSubjectExceptionSupplier;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentSubjectUpdateAction {

    private final StudentSubjectRepository repository;

    public StudentSubject update(StudentSubjectUpdateDTO update) {
        StudentSubject studentSubject =
                this.findEagerByEnrollmentSubjectSemesterClass(
                        update.getEnrollmentNumber(),
                        update.getSubjectCode(),
                        update.getSemester(),
                        update.getClassNumber());
        studentSubject.setGrade(update.getGrade());

        return repository.save(studentSubject);
    }

    private StudentSubject findEagerByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        return repository.findEagerByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber)
                .orElseThrow(StudentSubjectExceptionSupplier.notFoundByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber));
    }

}
