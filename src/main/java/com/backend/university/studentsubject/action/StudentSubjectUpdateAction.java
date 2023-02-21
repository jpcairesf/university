package com.backend.university.studentsubject.action;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectUpdateDTO;
import com.backend.university.studentsubject.dto.mapper.StudentSubjectMapper;
import com.backend.university.studentsubject.exception.StudentSubjectExceptionSupplier;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudentSubjectUpdateAction {

    private final StudentSubjectRepository repository;

    @Transactional
    public StudentSubjectOutputDTO update(StudentSubjectUpdateDTO update) {
        StudentSubject studentSubject =
                this.findEagerByEnrollmentSubjectSemesterClass(
                        update.getEnrollmentNumber(),
                        update.getSubjectCode(),
                        update.getSemester(),
                        update.getClassNumber());
        studentSubject.setSemester(update.getSemester());
        studentSubject.setGrade(update.getGrade());

        repository.save(studentSubject);
        return StudentSubjectMapper.entityToOutput(studentSubject);
    }

    private StudentSubject findEagerByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        return repository.findEagerByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber)
                .orElseThrow(StudentSubjectExceptionSupplier.notFoundByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber));
    }

}
