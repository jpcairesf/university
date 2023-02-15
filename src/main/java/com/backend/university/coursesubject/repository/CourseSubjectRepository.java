package com.backend.university.coursesubject.repository;

import com.backend.university.coursesubject.domain.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Long> {

    Optional<CourseSubject> findByCourseNameAndSubjectCode(String courseName, String code);

    boolean existsByCourseNameAndSubjectCode(String courseName, String code);

}
