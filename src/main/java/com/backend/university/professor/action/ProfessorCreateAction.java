package com.backend.university.professor.action;

import com.backend.university.department.action.DepartmentRelatedAction;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorInputDTO;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.backend.university.professor.domain.enumx.Degree.toDegree;
import static com.backend.university.professor.domain.enumx.Rank.toRank;

@Component
@RequiredArgsConstructor
public class ProfessorCreateAction {

    private final ProfessorRepository repository;

    private final ProfessorValidatorAction validatorAction;

    private final DepartmentRelatedAction departmentRelatedAction;

    public Professor create(ProfessorInputDTO input) {
        validatorAction.validateExistsByCpf(input.getCpf());
        validatorAction.validateCpf(input.getCpf());

        Professor professor = new Professor();
        professor.setCpf(input.getCpf());
        professor.setName(input.getName());
        professor.setEmail(input.getEmail());
        professor.setBirthDate(input.getBirthDate());
        professor.setHiringDate(input.getHiringDate());
        professor.setDepartment(departmentRelatedAction.findEntityByName(input.getDepartment()));
        professor.setRank(toRank(input.getRank()));
        professor.setDegree(toDegree(input.getDegree()));

        return repository.save(professor);
    }
}
