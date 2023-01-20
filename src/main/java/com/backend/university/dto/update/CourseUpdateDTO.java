package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CourseUpdateDTO {

    private Long id;

    private String name;

    private String department;

}
