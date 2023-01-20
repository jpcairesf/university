package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SecretaryUpdateDTO {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private LocalDate birthDate;

    private LocalDate hiringDate;

    private String institute;

    private String tenderNotice;

}
