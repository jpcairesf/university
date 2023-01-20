package com.backend.university.course.repository;

import com.backend.university.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    boolean existsByName(String name);

    List<Course> findByDepartmentName(String departmentName);

    @Query("SELECT dep.name FROM Course course" +
            " JOIN course.department dep" +
            " WHERE course.id = :id")
    Optional<String> getDepartmentNameById(Long id);

    @Query("SELECT course FROM Course course" +
            " JOIN course.department dep" +
            " JOIN course.courseSubject cs" +
            " WHERE course.id = :id")
    Optional<Course> retrieveById(Long id);

}
