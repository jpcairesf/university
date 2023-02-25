package com.backend.university.studentsubject.domain;

import com.backend.university.student.domain.Student;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "STUDENT_SUBJECT")
public class StudentSubject {

    @EmbeddedId
    private StudentSubjectId id = new StudentSubjectId();

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @MapsId("subjectOfferId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBJECT_OFFER_ID", nullable = false)
    private SubjectOffer subjectOffer;

    @Column(name = "SEMESTER", nullable = false)
    private int semester;

    @Column(name = "GRADE")
    private BigDecimal grade;

}
