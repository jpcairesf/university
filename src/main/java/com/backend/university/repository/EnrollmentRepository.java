package com.backend.university.repository;

import com.backend.university.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentName(String name);

    Optional<Enrollment> findByNumber(int number);

    Optional<Enrollment> findByStudentCpf(String cpf);

    List<Enrollment> findByCourseName(String courseName);

    Optional<Long> findIdByNumber(int number);

}
