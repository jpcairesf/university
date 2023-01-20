package com.backend.university.institute.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "INSTITUTE_SEQ", sequenceName = "INSTITUTE_SEQ")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "INSTITUTE", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_INSTITUTE_NAME", columnNames = {"NAME"})})
public class Institute {

    @Id
    @GeneratedValue(generator = "INSTITUTE_SEQ")
    @Column(name = "INSTITUTE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "FOUNDATION_DATE", nullable = false)
    private LocalDate foundationDate;

}
