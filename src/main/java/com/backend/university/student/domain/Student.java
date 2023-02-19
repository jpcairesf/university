package com.backend.university.student.domain;

import com.backend.university.course.domain.Course;
import com.backend.university.studentsubject.domain.StudentSubject;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "STUDENT_SEQ", sequenceName = "STUDENT_SEQ")
@Table(name = "STUDENT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_STUDENT_ENROLLMENT_NUMBER", columnNames = {"ENROLLMENT_NUMBER"}),
        @UniqueConstraint(name = "UQ_STUDENT_CPF", columnNames = {"CPF"})})
public class Student {

    @Id
    @GeneratedValue(generator = "STUDENT_SEQ")
    @Column(name = "STUDENT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_SUBJECT_ID")
    private Set<StudentSubject> studentSubjects;

    @Column(name = "ENROLLMENT_NUMBER", nullable = false)
    private int enrollmentNumber;

    @Column(name = "ENROLLMENT_DATE", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

}
