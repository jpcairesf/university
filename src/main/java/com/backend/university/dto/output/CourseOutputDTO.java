package com.backend.university.dto.output;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CourseOutputDTO {

    private Long id;

    private String name;

    private String department;

    private Set<CourseSubjectOutputDTO> courseSubjects;

    private int courseLoad;

}
