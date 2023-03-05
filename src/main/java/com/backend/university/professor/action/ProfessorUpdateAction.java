package com.backend.university.professor.action;

import com.backend.university.department.action.DepartmentRelatedAction;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorUpdateDTO;
import com.backend.university.professor.exception.ProfessorExceptionSupplier;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.professor.domain.enumx.Degree.toDegree;
import static com.backend.university.professor.domain.enumx.Rank.toRank;

@Component
@RequiredArgsConstructor
public class ProfessorUpdateAction {

    private final ProfessorRepository repository;

    private final ProfessorValidatorAction validatorAction;

    private final DepartmentRelatedAction departmentRelatedAction;

    @Transactional
    public Professor update(ProfessorUpdateDTO update) {
        Professor professor = this.findEntityById(update.getId());

        if (!professor.getDepartment().getName().equalsIgnoreCase(update.getDepartment())) {
            professor.setDepartment(departmentRelatedAction.findEntityByName(update.getDepartment()));
        }
        if (!update.getCpf().equalsIgnoreCase(professor.getCpf())) {
            validatorAction.validateExistsByCpf(update.getCpf());
            validatorAction.validateCpf(update.getCpf());
            professor.setCpf(update.getCpf());
        }
        professor.setName(update.getName());
        professor.setEmail(update.getEmail());
        professor.setBirthDate(update.getBirthDate());
        professor.setHiringDate(update.getHiringDate());
        professor.setRank(toRank(update.getRank()));
        professor.setDegree(toDegree(update.getDegree()));

        return repository.save(professor);
    }

    private Professor findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(ProfessorExceptionSupplier.notFoundById(id));
    }

}
