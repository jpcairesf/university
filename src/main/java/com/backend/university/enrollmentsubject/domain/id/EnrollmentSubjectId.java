package com.backend.university.enrollmentsubject.domain.id;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EnrollmentSubjectId implements Serializable {

    private static final long serialVersionUID = 8978894067704319617L;

    private Long enrollmentId;

    private Long subjectId;

}