package com.backend.university.coursesubject.domain;

import com.backend.university.course.domain.Course;
import com.backend.university.subject.domain.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "COURSE_SUBJECT_SEQ", sequenceName = "COURSE_SUBJECT_SEQ")
@Table(name = "COURSE_SUBJECT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_CRSE_SUBJ_CURRICULUM_YEAR", columnNames = {"COURSE_ID", "SUBJECT_ID, CURRICULUM_YEAR"})})
public class CourseSubject {

    @Id
    @GeneratedValue(generator = "COURSE_SUBJECT_SEQ")
    @Column(name = "COURSE_SUBJECT_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @Column(name = "REQUIRED", nullable = false)
    private boolean required;

    @Column(name = "CURRICULUM_YEAR")
    private int curriculumYear;

}
