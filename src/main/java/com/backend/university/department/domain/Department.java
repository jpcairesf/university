package com.backend.university.department.domain;

import com.backend.university.institute.domain.Institute;
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
@SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ")
@Table(name = "DEPARTMENT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_DEPARTMENT_NAME", columnNames = {"NAME"})})
public class Department {

    @Id
    @GeneratedValue(generator = "DEPARTMENT_SEQ")
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTITUTE_ID", nullable = false)
    private Institute institute;

    @Column(name = "NAME", nullable = false)
    private String name;

}
