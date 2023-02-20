package com.backend.university.professor.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.department.service.DepartmentService;
import com.backend.university.professor.domain.Professor;
import com.backend.university.professor.dto.ProfessorOutputDTO;
import com.backend.university.professor.dto.ProfessorUpdateDTO;
import com.backend.university.professor.dto.mapper.ProfessorMapper;
import com.backend.university.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.professor.domain.enumx.Degree.toDegree;
import static com.backend.university.professor.domain.enumx.Rank.toRank;
import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ProfessorUpdateAction {

    private final ProfessorRepository repository;

    private final ProfessorValidatorAction validatorAction;

    private final DepartmentService departmentService;

    @Transactional
    public ProfessorOutputDTO update(ProfessorUpdateDTO update) {
        Professor professor = this.findEntityById(update.getId());

        if (!professor.getDepartment().getName().equalsIgnoreCase(update.getDepartment())) {
            professor.setDepartment(departmentService.findEntityByName(update.getName()));
        }
        if (!update.getCpf().equalsIgnoreCase(professor.getCpf())) {
            this.validatorAction.validateExistsByCpf(update.getCpf());
            professor.setCpf(update.getCpf());
        }
        professor.setName(update.getName());
        professor.setEmail(update.getEmail());
        professor.setBirthDate(update.getBirthDate());
        professor.setHiringDate(update.getHiringDate());
        professor.setRank(toRank(update.getRank()));
        professor.setDegree(toDegree(update.getDegree()));

        repository.save(professor);
        return ProfessorMapper.entityToOutput(professor);
    }

    private Professor findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no professor with ID \"%s\".", id)));
    }

}
