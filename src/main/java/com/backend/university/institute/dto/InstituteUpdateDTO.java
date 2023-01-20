package com.backend.university.institute.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InstituteUpdateDTO {

    private Long id;

    private String name;

    private LocalDate foundationDate;

}
