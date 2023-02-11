package com.backend.university.student.repository;

import com.backend.university.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByNumber(int number);

    Optional<Student> findByStudentCpf(String cpf);

    List<Student> findByCourseName(String courseName);

    Optional<Long> findIdByNumber(int number);

    boolean existsByNumber(int number);
}
