package com.backend.university.employee.dto.mapper;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeMapper {

    public static EmployeeOutputDTO entityToOutput(Employee employee) {
        return EmployeeOutputDTO.builder()
                .cpf(employee.getCpf())
                .name(employee.getName())
                .email(employee.getEmail())
                .birthDate(employee.getBirthDate())
                .hiringDate(employee.getHiringDate())
                .build();
    }

}
