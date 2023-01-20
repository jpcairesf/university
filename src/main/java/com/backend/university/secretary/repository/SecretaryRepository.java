package com.backend.university.secretary.repository;

import com.backend.university.secretary.domain.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, Long> {

    Optional<Secretary> findByCpf(String cpf);

    List<Secretary> findByName(String name);

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

    List<Secretary> findByInstituteName(String instituteName);

    List<Secretary> findByTenderNotice(String tenderNotice);

    List<Secretary> findByInstituteNameAndTenderNotice(String instituteName, String tenderNotice);

}
