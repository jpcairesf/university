package com.backend.university.professor.repository;

import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.domain.enumx.Degree;
import com.backend.university.professor.domain.enumx.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByCpf(String cpf);

    List<Professor> findByName(String name);

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

    List<Professor> findByDepartmentName(String departmentName);

}
