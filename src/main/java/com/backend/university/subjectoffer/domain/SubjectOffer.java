package com.backend.university.subjectoffer.domain;

import com.backend.university.course.domain.Course;
import com.backend.university.professor.domain.Professor;
import com.backend.university.room.domain.Room;
import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subjectoffer.enumx.AmPmEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "SUBJECT_OFFER_SEQ", sequenceName = "SUBJECT_OFFER_SEQ")
@Table(name = "SUBJECT_OFFER", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_SUBJECT_OFFER_COURSE_SUBJECT_SEMESTER_CLASS", columnNames = {"COURSE_ID", "SUBJECT_ID", "SEMESTER", "CLASS_NUMBER"})})
public class SubjectOffer {

    @Id
    @GeneratedValue(generator = "SUBJECT_OFFER_SEQ")
    @Column(name = "SUBJECT_OFFER_ID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_SUBJECT_ID")
    private Set<StudentSubject> studentSubjects = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESSOR_ID", nullable = false)
    private Professor professor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "DAY_OF_WEEK", nullable = false)
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "AM_PM", nullable = false)
    private AmPmEnum amPm;

    @Column(name = "SEMESTER", nullable = false)
    private int semester;

    @Column(name = "CLASS_NUMBER", nullable = false)
    private int classNumber;

    @Column(name = "DURATION_MIN", nullable = false)
    private int durationMin;

    @Column(name = "VACANCIES", nullable = false)
    private int vacancies;

}
