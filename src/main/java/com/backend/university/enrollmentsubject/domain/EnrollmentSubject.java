package com.backend.university.enrollmentsubject.domain;

import com.backend.university.subject.domain.Subject;
import com.backend.university.enrollmentsubject.domain.id.EnrollmentSubjectId;
import com.backend.university.enrollment.domain.Enrollment;
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
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ENRL_SUBJ", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ENRL_SUBJ_ID_SEMESTER", columnNames = {"ENROLLMENT_ID", "SUBJECT_ID", "SEMESTER"})})
public class EnrollmentSubject {

    @EmbeddedId
    private EnrollmentSubjectId id = new EnrollmentSubjectId();

    @MapsId("enrollmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENROLLMENT_ID", nullable = false)
    private Enrollment enrollment;

    @MapsId("subjectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @Column(name = "SEMESTER", nullable = false)
    private int semester;

    @Column(name = "GRADE")
    private BigDecimal grade;

}
