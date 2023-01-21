package com.backend.university.course.domain;

import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.department.domain.Department;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CourseSubject> courseSubjects;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COURSE_LOAD", nullable = false)
    private int courseLoad;

    public Set<CourseSubject> getCourseSubjects() {
        return Collections.unmodifiableSet(this.courseSubjects);
    }

    public void setCourseSubjects(Set<CourseSubject> courseSubjects) {
        courseSubjects.forEach(s -> s.setCourse(this));
        this.courseSubjects = courseSubjects;
    }

    public void addCourseSubject(CourseSubject courseSubject) {
        courseSubject.setCourse(this);
        this.courseSubjects.add(courseSubject);
    }

    public void addCourseSubjects(Set<CourseSubject> courseSubjects) {
        courseSubjects.forEach(s -> s.setCourse(this));
        this.courseSubjects.addAll(courseSubjects);
    }

    public void updateCourseSubject(CourseSubject subject, CourseSubject updatedSubject) {
        this.courseSubjects.remove(subject);
        this.courseSubjects.add(updatedSubject);
    }

    public void removeCourseSubject(String subjectCode) {
        this.courseSubjects.removeIf(subject -> subject.getSubject().getName().equalsIgnoreCase(subjectCode));
    }
}
