package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Builder
public class CourseUpdateDTO {

    private Long id;

    private String name;

    private String department;

    private List<CourseSubjectUpdateDTO> courseSubjects;

    public void addCourseSubject(CourseSubjectUpdateDTO courseSubject) {
        courseSubject.setCourse(this.name);
        this.courseSubjects.add(courseSubject);
    }

    public void removeCourseSubject(CourseSubjectUpdateDTO courseSubject) {
        this.courseSubjects.removeIf(c -> Objects.equals(c.getId(), courseSubject.getId()));
    }

}
