package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InstituteOutputDTO {

    private Long id;

    private String name;

    private LocalDate foundationDate;

}
