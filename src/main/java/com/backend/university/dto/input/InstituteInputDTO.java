package com.backend.university.dto.input;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InstituteInputDTO {

    private String name;

    private LocalDate foundationDate;

}
