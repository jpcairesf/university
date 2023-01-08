package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecretaryUpdateDTO {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private String birthDate;

    private String hiringDate;

    private String institute;

    private String tenderNotice;

}
