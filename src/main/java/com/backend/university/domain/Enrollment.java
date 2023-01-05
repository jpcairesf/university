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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@SequenceGenerator(name = "ENROLLMENT_SEQ", sequenceName = "ENROLLMENT_SEQ")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ENROLLMENT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ENROLLMENT_CODE", columnNames = {"CODE"}),
        @UniqueConstraint(name = "UQ_ENROLLMENT_STUDENT", columnNames = {"STUDENT_ID"})})
public class Enrollment {

    @Id
    @GeneratedValue(generator = "ENROLLMENT_SEQ")
    @Column(name = "ENROLLMENT_ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "ENROLLMENT_DATE", nullable = false)
    private Date enrollmentDate;

}
