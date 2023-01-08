package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.util.List;

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
        this.courseSubjects.remove(courseSubject);
    }

    public void removeCourseSubject(String subjectCode) {
        this.courseSubjects.removeIf(s -> s.getSubjectCode().equalsIgnoreCase(subjectCode));
    }

}
