package com.backend.university.repository;

import com.backend.university.domain.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Long> {



}
