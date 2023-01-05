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
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "ENRL_SUBJ_SEQ", sequenceName = "ENRL_SUBJ_SEQ")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ENRL_SUBJ", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ENRL_SUBJ_ID_SEMESTER", columnNames = {"ENROLLMENT_ID", "SUBJECT_ID", "SEMESTER"})})
public class EnrollmentSubject {

    @Id
    @GeneratedValue(generator = "ENRL_SUBJ_SEQ")
    @Column(name = "ENRL_SUBJ_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENROLLMENT_ID", nullable = false)
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @Column(name = "SEMESTER", nullable = false)
    private int semester;

    @Column(name = "GRADE")
    private BigDecimal grade;

}
