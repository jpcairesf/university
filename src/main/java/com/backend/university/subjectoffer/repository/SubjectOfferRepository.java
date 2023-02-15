package com.backend.university.subjectoffer.repository;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectOfferRepository extends JpaRepository<SubjectOffer, Long> {

    @Query("SELECT soff FROM SubjectOffer soff" +
            "   INNER JOIN soff.subject AS sbj" +
            "   WHERE soff.course.id = :courseId" +
            "   AND sbj.code = :subjectCode" +
            "   AND soff.semester = :semester" +
            "   AND soff.classNumber = :classNumber")
    Optional<SubjectOffer> findIdByCourseSubjectSemesterClass(Long courseId, String subjectCode, int semester, int classNumber);

    boolean existsByCourseNameAndSubjectCodeAndSemesterAndClassNumber(String courseName, String subjectCode, int semester, int classNumber);
}
