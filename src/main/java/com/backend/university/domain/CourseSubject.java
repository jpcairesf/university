package com.backend.university.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "CRSE_SUBJ_SEQ", sequenceName = "CRSE_SUBJ_SEQ")
@Table(name = "CRSE_SUBJ", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_CRSE_SUBJ_ID", columnNames = {"COURSE_ID", "SUBJECT_ID"})})
public class CourseSubject {

    @Id
    @GeneratedValue(generator = "CRSE_SUBJ_SEQ")
    @Column(name = "CRSE_SUBJ_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @Column(name = "REQUIRED", nullable = false)
    private boolean required;

    @Column(name = "SEMESTER", nullable = false)
    private int semester;

}
