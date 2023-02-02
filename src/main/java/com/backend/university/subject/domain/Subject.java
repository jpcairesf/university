package com.backend.university.subject.domain;

import com.backend.university.enrollmentsubject.domain.EnrollmentSubject;
import com.backend.university.professor.domain.Professor;
import com.backend.university.room.domain.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "SUBJECT_SEQ", sequenceName = "SUBJECT_SEQ")
@Table(name = "SUBJECT", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_SUBJECT_CODE", columnNames = {"CODE"}),
        @UniqueConstraint(name = "UQ_SUBJECT_ROOM_SCHD", columnNames = {"ROOM_ID", "SCHEDULE"})})
public class Subject {

    @Id
    @GeneratedValue(generator = "SUBJECT_SEQ")
    @Column(name = "SUBJECT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESSOR_ID", nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "subject")
    @OrderBy("SEMESTER DESC, NAME ASC")
    private List<EnrollmentSubject> enrollmentSubjects;

    @Column(name = "SCHEDULE")
    private String schedule;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STUDY_LOAD", nullable = false)
    private int studyLoad;

    @Column(name = "VACANCIES")
    private int vacancies;

}
