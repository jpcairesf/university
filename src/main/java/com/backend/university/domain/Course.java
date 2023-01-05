package com.backend.university.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "COURSE_SEQ", sequenceName = "COURSE_SEQ")
@Table(name = "COURSE", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_COURSE_NAME", columnNames = {"NAME"})})
public class Course {

    @Id
    @GeneratedValue(generator = "COURSE_SEQ")
    @Column(name = "COURSE_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARMENT_ID", nullable = false)
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    @OrderBy("SEMESTER DESC, NAME ASC")
    private List<CourseSubject> courseSubjects = new ArrayList<>();

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COURSE_LOAD", nullable = false)
    private int courseLoad;

    public void setCourseSubjects(List<CourseSubject> courseSubjects) {
        courseSubjects.forEach(s -> s.setCourse(this));
        this.courseSubjects = courseSubjects;
    }

    public void addCourseSubject(CourseSubject courseSubject) {
        courseSubject.setCourse(this);
        this.courseSubjects.add(courseSubject);
    }
}
