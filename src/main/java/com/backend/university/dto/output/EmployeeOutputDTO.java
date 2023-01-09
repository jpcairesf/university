package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeOutputDTO {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private String birthDate;

    private String hiringDate;

}
