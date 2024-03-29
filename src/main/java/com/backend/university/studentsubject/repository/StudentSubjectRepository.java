package com.backend.university.studentsubject.repository;

import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectId> {

    @Query(value = "SELECT ssbj FROM StudentSubject ssbj" +
            "   INNER JOIN ssbj.student AS std" +
            "   INNER JOIN ssbj.subjectOffer AS soff" +
            "   INNER JOIN soff.subject AS sbj" +
            "   WHERE std.enrollmentNumber = :enrollmentNumber" +
            "   AND sbj.code = :subjectCode" +
            "   AND soff.classNumber = :classNumber" +
            "   AND soff.semester = :semester" +
            "   AND soff.course.id = std.course.id")
    Optional<StudentSubject> findByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber);

    @Query(value = "SELECT COUNT(*) > 0 FROM StudentSubject ssbj" +
            "   INNER JOIN ssbj.student AS std" +
            "   INNER JOIN ssbj.subjectOffer AS soff" +
            "   INNER JOIN soff.subject AS sbj" +
            "   WHERE std.enrollmentNumber = :enrollmentNumber" +
            "   AND sbj.code = :subjectCode" +
            "   AND soff.classNumber = :classNumber" +
            "   AND soff.semester = :semester" +
            "   AND soff.course.id = std.course.id")
    boolean existsByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber);

    int countBySubjectOffer(SubjectOffer subjectOffer);
}
