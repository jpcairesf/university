package com.backend.university.professor.action;

import com.backend.university.department.service.DepartmentService;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorInputDTO;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.professor.domain.enumx.Degree.toDegree;
import static com.backend.university.professor.domain.enumx.Rank.toRank;

@Component
@RequiredArgsConstructor
public class ProfessorCreateAction {

    private final ProfessorRepository repository;

    private final ProfessorValidatorAction validatorAction;

    private final DepartmentService departmentService;

    @Transactional
    public ProfessorOutputDTO create(ProfessorInputDTO input) {
        this.validatorAction.validateCpf(input.getCpf());
        this.validatorAction.validateExistsByCpf(input.getCpf());

        Professor professor = new Professor();
        professor.setCpf(input.getCpf());
        professor.setName(input.getName());
        professor.setEmail(input.getEmail());
        professor.setBirthDate(input.getBirthDate());
        professor.setHiringDate(input.getHiringDate());
        professor.setDepartment(departmentService.findEntityByName(input.getDepartment()));
        professor.setRank(toRank(input.getRank()));
        professor.setDegree(toDegree(input.getDegree()));

        repository.save(professor);
        return ProfessorMapper.entityToOutput(professor);
    }
}
