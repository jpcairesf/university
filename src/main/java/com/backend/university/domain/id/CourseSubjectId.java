package com.backend.university.domain.id;

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
public class CourseSubjectId implements Serializable {

    private static final long serialVersionUID = 955234759569909716L;
    private long courseId;

    private long subjectId;

}
