package com.backend.university.domain;

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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "department")
    @OrderBy("NAME ASC")
    private List<Professor> professors = new ArrayList<>();

    @Column(name = "NAME", nullable = false)
    private String name;

    public void setProfessors(List<Professor> professors) {
        professors.forEach(p -> p.setDepartment(this));
        this.professors = professors;
    }

    public void addProfessor(Professor professor) {
        professor.setDepartment(this);
        this.professors.add(professor);
    }
}
