package com.backend.university.employee.dto.mapper;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeMapper {

    public Employee inputToEntity(EmployeeInputDTO input) {
        Employee employee = new Employee();
        employee.setCpf(input.getCpf());
        employee.setName(input.getName());
        employee.setEmail(input.getEmail());
        employee.setBirthDate(input.getBirthDate());
        employee.setHiringDate(input.getHiringDate());
        return employee;
    }

    public EmployeeOutputDTO entityToOutput(Employee employee) {
        return EmployeeOutputDTO.builder()
                .cpf(employee.getCpf())
                .name(employee.getName())
                .email(employee.getEmail())
                .birthDate(employee.getBirthDate())
                .hiringDate(employee.getHiringDate())
                .build();
    }

}
