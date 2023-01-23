package com.backend.university.enrollmentsubject.repository;

import com.backend.university.enrollmentsubject.domain.EnrollmentSubject;
import com.backend.university.enrollmentsubject.domain.id.EnrollmentSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentSubjectRepository extends JpaRepository<EnrollmentSubject, EnrollmentSubjectId> {

    Optional<EnrollmentSubject> findByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester);

    boolean existsByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester);

}
