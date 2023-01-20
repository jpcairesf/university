package com.backend.university.professor.domain;

import com.backend.university.department.domain.Department;
import com.backend.university.professor.domain.enumx.Degree;
import com.backend.university.professor.domain.enumx.Rank;
import com.backend.university.employee.domain.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "PROFESSOR_ID")
public class Professor extends Employee {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARMENT_ID", nullable = false)
    private Department department;

    @Column(name = "RANK", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(name = "DEGREE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Degree degree;

}
