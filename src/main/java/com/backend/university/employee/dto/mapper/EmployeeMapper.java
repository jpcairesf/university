package com.backend.university.employee.dto.mapper;

import com.backend.university.employee.domain.Employee;
import com.backend.university.employee.dto.EmployeeInputDTO;
import com.backend.university.employee.dto.EmployeeOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
