package com.backend.university.subjectoffer.repository;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectOfferRepository extends JpaRepository<SubjectOffer, Long> {

    @Query("SELECT soff.id FROM SubjectOffer soff" +
            "   INNER JOIN Subject sbj" +
            "   INNER JOIN Course crs" +
            "   WHERE crs.name = :courseName" +
            "   AND sbj.code = :subjectCode" +
            "   AND soff.semester = :semester" +
            "   AND soff.classNumber = :classNumber")
    Optional<Long> findIdByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber);

    boolean existsByCourseNameAndSubjectCodeAndSemesterAndClassNumber(String courseName, String subjectCode, int semester, int classNumber);
}
