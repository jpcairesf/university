package com.backend.university.dto.mapper;

import com.backend.university.domain.Professor;
import com.backend.university.dto.input.ProfessorInputDTO;
import com.backend.university.dto.output.ProfessorOutputDTO;
import com.backend.university.dto.update.ProfessorUpdateDTO;
import com.backend.university.service.DepartmentService;
import com.backend.university.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;
import static com.backend.university.domain.enumx.Degree.toDegree;
import static com.backend.university.domain.enumx.Rank.toRank;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessorMapper {

    private final ProfessorService professorService;

    private final DepartmentService departmentService;

    public Professor inputToEntity(ProfessorInputDTO input) {
        Professor professor = new Professor();
        professor.setCpf(input.getCpf());
        professor.setName(input.getName());
        professor.setEmail(input.getEmail());
        professor.setBirthDate(input.getBirthDate());
        professor.setHiringDate(input.getHiringDate());
        professor.setDepartment(departmentService.findEntityByName(input.getDepartment()));
        professor.setRank(toRank(input.getRank()));
        professor.setDegree(toDegree(input.getDegree()));
        return professor;
    }

    public Professor updateToEntity(ProfessorUpdateDTO update) {
        Professor professor = professorService.findEntityById(update.getId());
        if (!professor.getDepartment().getName().equalsIgnoreCase(update.getDepartment())) {
            professor.setDepartment(departmentService.findEntityByName(update.getName()));
        }
        setIfNotNull(update.getCpf(), professor::setCpf);
        setIfNotNull(update.getName(), professor::setName);
        setIfNotNull(update.getEmail(), professor::setEmail);
        setIfNotNull(update.getBirthDate(), professor::setBirthDate);
        setIfNotNull(update.getHiringDate(), professor::setHiringDate);
        setIfNotNull(update.getRank(), rank -> professor.setRank(toRank(rank)));
        setIfNotNull(update.getDegree(), degree -> professor.setDegree(toDegree(degree)));
        return professor;
    }

    public ProfessorOutputDTO entityToOutput(Professor professor) {
        return ProfessorOutputDTO.builder()
                .cpf(professor.getCpf())
                .name(professor.getName())
                .email(professor.getEmail())
                .birthDate(professor.getBirthDate())
                .hiringDate(professor.getHiringDate())
                .department(professor.getDepartment().getName())
                .rank(professor.getRank())
                .degree(professor.getDegree())
                .build();
    }

}
