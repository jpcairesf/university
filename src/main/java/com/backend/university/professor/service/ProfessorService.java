package com.backend.university.professor.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.service.DepartmentService;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorInputDTO;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.ProfessorUpdateDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.professor.domain.enumx.Degree.toDegree;
import static com.backend.university.professor.domain.enumx.Rank.toRank;
import static java.lang.String.format;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessorService {

    private final ProfessorRepository repository;

    private final ProfessorMapper mapper;

    private final DepartmentService departmentService;

    @Transactional
    public ProfessorOutputDTO create(ProfessorInputDTO input) {
        this.validateExistsByCpf(input.getCpf());
        Professor professor = mapper.inputToEntity(input);
        repository.save(professor);
        return mapper.entityToOutput(professor);
    }

    @Transactional
    public ProfessorOutputDTO update(ProfessorUpdateDTO update) {
        Professor professor = this.findEntityById(update.getId());

        if (!professor.getDepartment().getName().equalsIgnoreCase(update.getDepartment())) {
            professor.setDepartment(departmentService.findEntityByName(update.getName()));
        }
        if (!update.getCpf().equalsIgnoreCase(professor.getCpf())) {
            this.validateExistsByCpf(update.getCpf());
            professor.setCpf(update.getCpf());
        }
        professor.setName(update.getName());
        professor.setEmail(update.getEmail());
        professor.setBirthDate(update.getBirthDate());
        professor.setHiringDate(update.getHiringDate());
        professor.setRank(toRank(update.getRank()));
        professor.setDegree(toDegree(update.getDegree()));
        repository.save(professor);
        return mapper.entityToOutput(professor);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Professor findEntityByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException(format("There is no professor with CPF \"%s\".", cpf)));
    }

    public Professor findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no professor with ID \"%s\".", id)));
    }

    private void validateExistsByCpf(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new BusinessException(format("There already a professor with CPF \"%s\".", cpf));
        }
    }
}
