package com.backend.university.enrollmentsubject.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentSubjectId implements Serializable {

    private static final long serialVersionUID = 8978894067704319617L;

    private Long enrollmentId;

    private Long subjectId;

}
