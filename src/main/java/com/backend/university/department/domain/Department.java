package com.backend.university.department.domain;

import com.backend.university.institute.domain.Institute;
import com.backend.university.professor.domain.Professor;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Professor> professors = new HashSet<>();

    @Column(name = "NAME", nullable = false)
    private String name;

    public Set<Professor> getProfessors() {
        return Collections.unmodifiableSet(this.professors);
    }

    public void setProfessors(Set<Professor> professors) {
        professors.forEach(p -> p.setDepartment(this));
        this.professors = professors;
    }

    public void addProfessor(Professor professor) {
        professor.setDepartment(this);
        this.professors.add(professor);
    }

    public void updateProfessors(Professor professor, Professor updatedProfessor) {
        this.removeProfessor(professor);
        this.addProfessor(updatedProfessor);
    }

    public void addProfessors(Set<Professor> professors) {
        professors.forEach(p -> p.setDepartment(this));
        this.professors.addAll(professors);
    }

    public void removeProfessor(Professor professor) {
        this.professors.remove(professor);
    }
}
