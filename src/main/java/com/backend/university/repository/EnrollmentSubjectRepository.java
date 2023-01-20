package com.backend.university.repository;

import com.backend.university.domain.EnrollmentSubject;
import com.backend.university.domain.id.EnrollmentSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentSubjectRepository extends JpaRepository<EnrollmentSubject, EnrollmentSubjectId> {

    int countBySubjectCode(String code);

    int countByEnrollmentNumber(int number);

    List<EnrollmentSubject> findBySubjectCodeAndSemester(String code, int semester);

    List<EnrollmentSubject> findByEnrollmentNumberAndSemester(int number, int semester);

    List<EnrollmentSubject> findBySubjectCodeOrderBySemesterDesc(String code);

    List<EnrollmentSubject> findByEnrollmentNumberOrderBySemesterDesc(int number);

    Optional<EnrollmentSubject> findByEnrollmentNumberAndSubjectCodeAndSemester(int number, String code, int semester);

}
