package com.backend.university.dto.mapper;

import com.backend.university.domain.Department;
import com.backend.university.domain.Institute;
import com.backend.university.domain.Professor;
import com.backend.university.dto.input.DepartmentInputDTO;
import com.backend.university.dto.output.DepartmentOutputDTO;
import com.backend.university.dto.output.ProfessorOutputDTO;
import com.backend.university.dto.update.DepartmentUpdateDTO;
import com.backend.university.dto.update.ProfessorUpdateDTO;
import com.backend.university.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentMapper {

    private final InstituteService instituteService;

    private final ProfessorMapper professorMapper;

    public Department inputToEntity(DepartmentInputDTO input) {
        Department department = new Department();
        Institute institute = instituteService.findEntityByName(input.getInstitute());

        department.setName(input.getName());
        department.setInstitute(institute);
        department.setProfessors(new ArrayList<>());
        return department;
    }

    public Department updateToEntity(DepartmentUpdateDTO update, Department department) {
        if (!update.getName().equalsIgnoreCase(department.getName())) {
            department.setName(update.getName());
        }
        if (!update.getInstitute().equalsIgnoreCase(department.getInstitute().getName())) {
            department.setInstitute(instituteService.findEntityByName(update.getInstitute()));
        }
        if (!update.getProfessors().isEmpty()) {
            deleteProfessors(update.getProfessors(), department);
            createProfessors(update.getProfessors(), department);
            updateProfessors(update.getProfessors(), department);
        }
        return department;
    }

    private void createProfessors(List<ProfessorUpdateDTO> professorsUpdate, Department department) {
        List<Professor> professors = new ArrayList<>();

        professorsUpdate.stream()
                .filter(professorUpdate -> professorUpdate.getId() == null)
                .forEach(professorUpdate -> professors.add(professorMapper.updateToEntity(professorUpdate)));

        department.addProfessors(professors);
    }

    private void updateProfessors(List<ProfessorUpdateDTO> professorsUpdate, Department department) {
    }

    private void deleteProfessors(List<ProfessorUpdateDTO> professorsUpdate, Department department) {
    }

    public DepartmentOutputDTO entityToOutput(Department department) {
        List<ProfessorOutputDTO> professors = department.getProfessors().stream()
                .map(professorMapper::entityToOutput)
                .collect(Collectors.toList());

        return DepartmentOutputDTO.builder()
                .name(department.getName())
                .institute(department.getInstitute().getName())
                .professors(professors)
                .build();
    }

}
