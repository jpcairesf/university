package com.backend.university.student.repository;

import com.backend.university.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEnrollmentNumber(int enrollmentNumber);

    Optional<Student> findByCpf(String cpf);

    List<Student> findByCourseName(String courseName);

    Optional<Long> findIdByEnrollmentNumber(int enrollmentNumber);

    boolean existsByEnrollmentNumber(int enrollmentNumber);

    boolean existsByCpf(String cpf);
}
