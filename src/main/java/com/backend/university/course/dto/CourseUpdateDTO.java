package com.backend.university.course.dto;

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
