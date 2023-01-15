package com.backend.university.dto.mapper;

import com.backend.university.domain.Employee;
import com.backend.university.dto.input.EmployeeInputDTO;
import com.backend.university.dto.output.EmployeeOutputDTO;
import com.backend.university.dto.update.EmployeeUpdateDTO;
import com.backend.university.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeMapper {

    private final EmployeeService employeeService;

    public Employee inputToEntity(EmployeeInputDTO input) {
        Employee employee = new Employee();
        employee.setCpf(input.getCpf());
        employee.setName(input.getName());
        employee.setEmail(input.getEmail());
        employee.setBirthDate(input.getBirthDate());
        employee.setHiringDate(input.getHiringDate());
        return employee;
    }

    public Employee updateToEntity(EmployeeUpdateDTO update) {
        Employee employee = employeeService.findEntityById(update.getId());
        setIfNotNull(update.getCpf(), employee::setCpf);
        setIfNotNull(update.getName(), employee::setName);
        setIfNotNull(update.getEmail(), employee::setEmail);
        setIfNotNull(update.getBirthDate(), employee::setBirthDate);
        setIfNotNull(update.getHiringDate(), employee::setHiringDate);
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
