package com.backend.university.course.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseInputDTO {

    private String name;

    private String department;

}
