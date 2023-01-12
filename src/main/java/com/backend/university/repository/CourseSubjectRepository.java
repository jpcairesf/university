package com.backend.university.repository;

import com.backend.university.domain.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Long> {

    List<CourseSubject> findByCourseName(String courseName);

    List<CourseSubject> findByCourseNameAndRequired(String courseName, boolean required);

    List<CourseSubject> findByCourseNameAndSemester(String courseName, int semester);

    List<CourseSubject> findBySubjectCode(String code);

    Optional<CourseSubject> findByCourseNameAndSubjectCode(String courseName, String code);

    boolean existsByCourseNameAndSubjectCode(String courseName, String code);

}
