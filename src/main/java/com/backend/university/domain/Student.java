package com.backend.university.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "STUDENT_SEQ", sequenceName = "STUDENT_SEQ")
@Table(name = "STUDENT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_STUDENT_CPF", columnNames = {"CPF"})})
public class Student {

    @Id
    @GeneratedValue(generator = "STUDENT_SEQ")
    @Column(name = "STUDENT_ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ENROLLMENT_ID", nullable = false)
    private Enrollment enrollment;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

}
