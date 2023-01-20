package com.backend.university.enrollment.domain;

import com.backend.university.course.domain.Course;
import com.backend.university.enrollmentsubject.domain.EnrollmentSubject;
import com.backend.university.student.domain.Student;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;

@Entity
@SequenceGenerator(name = "ENROLLMENT_SEQ", sequenceName = "ENROLLMENT_SEQ")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ENROLLMENT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ENROLLMENT_NUMBER", columnNames = {"NUMBER"})})
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "enrollment")
    @OrderBy("SEMESTER DESC, NAME ASC")
    private List<EnrollmentSubject> enrollmentSubjects;

    @Column(name = "NUMBER", nullable = false)
    private int number;

    @Column(name = "ENROLLMENT_DATE", nullable = false)
    private LocalDate enrollmentDate;

}
