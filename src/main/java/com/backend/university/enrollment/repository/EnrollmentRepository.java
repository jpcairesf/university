package com.backend.university.enrollment.repository;

import com.backend.university.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByNumber(int number);

    Optional<Enrollment> findByStudentCpf(String cpf);

    List<Enrollment> findByCourseName(String courseName);

    Optional<Long> findIdByNumber(int number);

    boolean existsByNumber(int number);
}
