package com.backend.university.coursesubject.repository;

import com.backend.university.coursesubject.domain.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Long> {

    @Query("SELECT cs FROM CourseSubject cs" +
            "   INNER JOIN cs.course AS crs" +
            "   INNER JOIN cs.subject AS sbj" +
            "   WHERE crs.name = :courseName" +
            "   AND sbj.code = :subjectCode" +
            "   AND cs.curriculumYear = :curriculumYear")
    Optional<CourseSubject> findByCourseNameAndSubjectCodeAndCurriculumYear(String courseName, String subjectCode, int curriculumYear);

}
