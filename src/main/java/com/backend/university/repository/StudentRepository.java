package com.backend.university.repository;

import com.backend.university.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByCpf(String cpf);

    List<Student> findByName(String name);

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

    Optional<Student> findByEnrollmentNumber(int number);

}
