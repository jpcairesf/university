package com.backend.university.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "SECRETARY_ID")
public class Secretary extends Employee {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTITUTE_ID", nullable = false)
    private Institute institute;

    @Column(name = "TENDER_NOTICE", nullable = false)
    private String tenderNotice;

}
