package com.backend.university.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class EnrollmentSubjectId implements Serializable {

    private static final long serialVersionUID = 8978894067704319617L;

    private long enrollmentId;

    private long subjectId;

}
