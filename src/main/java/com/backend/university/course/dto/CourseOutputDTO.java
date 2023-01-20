package com.backend.university.course.dto;

import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
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
