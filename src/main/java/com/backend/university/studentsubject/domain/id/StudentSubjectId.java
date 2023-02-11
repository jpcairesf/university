package com.backend.university.studentsubject.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubjectId implements Serializable {

    private static final long serialVersionUID = 8978894067704319617L;

    private Long subjectOfferId;

    private Long subjectId;

}
