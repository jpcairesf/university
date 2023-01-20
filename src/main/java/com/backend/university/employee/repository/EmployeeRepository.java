package com.backend.university.employee.repository;

import com.backend.university.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCpf(String cpf);

    List<Employee> findByName(String name);

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

}
