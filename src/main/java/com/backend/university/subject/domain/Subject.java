package com.backend.university.subject.domain;

import com.backend.university.professor.domain.Professor;
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
@SequenceGenerator(name = "SUBJECT_SEQ", sequenceName = "SUBJECT_SEQ")
@Table(name = "SUBJECT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_SUBJECT_CODE", columnNames = {"CODE"})})
public class Subject {

    @Id
    @GeneratedValue(generator = "SUBJECT_SEQ")
    @Column(name = "SUBJECT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESSOR_ID", nullable = false)
    private Professor professor;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STUDY_LOAD", nullable = false)
    private int studyLoad;

}
