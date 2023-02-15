package com.backend.university.subject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.professor.service.ProfessorService;
import com.backend.university.room.service.RoomService;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectService {

    private final SubjectRepository repository;

    private final ProfessorService professorService;

    @Transactional
    public SubjectOutputDTO findById(Long id) {
        return SubjectMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional
    public List<SubjectOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(SubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubjectOutputDTO create(SubjectInputDTO input) {
        this.validateExistsByCode(input.getCode());

        Subject subject = new Subject();
        subject.setCode(input.getCode());
        subject.setName(input.getName());
        subject.setStudyLoad(input.getStudyLoad());
        subject.setProfessor(professorService.findEntityByCpf(input.getProfessorCpf()));

        repository.save(subject);
        return SubjectMapper.entityToOutput(subject);
    }

    @Transactional
    public SubjectOutputDTO update(SubjectUpdateDTO update) {
        Subject subject = this.findEntityById(update.getId());

        if (!update.getCode().equalsIgnoreCase(subject.getCode())) {
            this.validateExistsByCode(update.getCode());
            subject.setCode(update.getCode());
        }
        if (!update.getProfessorCpf().equalsIgnoreCase(subject.getProfessor().getCpf())) {
            subject.setProfessor(professorService.findEntityByCpf(update.getProfessorCpf()));
        }
        subject.setName(update.getName());
        subject.setStudyLoad(update.getStudyLoad());

        repository.save(subject);
        return SubjectMapper.entityToOutput(subject);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Long findIdByCode(String code) {
        return repository.findIdByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

    public Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no subject with ID \"%s\".", id)));
    }

    public Subject findEntityByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

    private void validateExistsByCode(String code) {
        if (repository.existsByCode(code)) {
            throw new BusinessException(format("There is already a subject with code \"%s\".", code));
        }
    }

}
