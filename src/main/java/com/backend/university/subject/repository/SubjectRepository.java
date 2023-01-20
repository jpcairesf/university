package com.backend.university.subject.repository;

import com.backend.university.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByCode(String code);

    boolean existsByCode(String code);

    List<Subject> findByName(String name);

    List<Subject> findByProfessorCpf(String cpf);

    List<Subject> findByRoomName(String roomName);

    Optional<Subject> findByRoomNameAndSchedule(String roomName, String schedule);

    Optional<Long> findIdByCode(String code);

}
