package com.backend.university.studentsubject.repository;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectId> {

    @Query("SELECT ssbj FROM StudentSubject ssbj" +
            "   JOIN FETCH ssbj.student AS std" +
            "   JOIN FETCH ssbj.subjectOffer AS soff" +
            "   JOIN FETCH std.subject AS sbj" +
            "   WHERE std.enrollmentNumber = :enrollmentNumber" +
            "   AND sbj.code = :subjectCode" +
            "   AND soff.classNumber = :classNumber" +
            "   AND ssbj.semester = :semester" +
            "   AND soff.course.id = std.course_id")
    Optional<StudentSubject> findEagerByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber);

    @Query("EXISTS ssbj FROM StudentSubject ssbj" +
            "   INNER JOIN ssbj.student AS std" +
            "   INNER JOIN ssbj.subjectOffer AS soff" +
            "   INNER JOIN std.subject AS sbj" +
            "   WHERE std.enrollmentNumber = :enrollmentNumber" +
            "   AND sbj.code = :subjectCode" +
            "   AND soff.classNumber = :classNumber" +
            "   AND ssbj.semester = :semester"+
            "   AND soff.course.id = std.course_id")
    boolean existsByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber);

}
