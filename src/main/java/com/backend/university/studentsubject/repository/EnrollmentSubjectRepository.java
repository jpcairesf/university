package com.backend.university.studentsubject.repository;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectId> {

    Optional<StudentSubject> findByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester);

    boolean existsByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester);

}
