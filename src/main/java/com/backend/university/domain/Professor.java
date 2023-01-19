package com.backend.university.domain;

import com.backend.university.domain.enumx.Degree;
import com.backend.university.domain.enumx.Rank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Rank rank;

    @Column(name = "DEGREE", nullable = false)
    private Degree degree;

}
