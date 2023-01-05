package com.backend.university.domain;

import com.backend.university.domain.id.CourseSubjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CRSE_SUBJ", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_CRSE_SUBJ_SEMESTER", columnNames = {"COURSE_ID", "SUBJECT_ID", "SEMESTER"})})
public class CourseSubject {

    @EmbeddedId
    private CourseSubjectId id = new CourseSubjectId();

    @MapsId("courseId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @MapsId("subjectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @Column(name = "REQUIRED", nullable = false)
    private boolean required;

    @Column(name = "SEMESTER", nullable = false)
    private int semester;

}
