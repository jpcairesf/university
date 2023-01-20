package com.backend.university.dto.update;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EnrollmentUpdateDTO {

    private Long id;

    private String studentCpf;

    private String course;

    private List<EnrollmentSubjectUpdateDTO> enrollmentSubjects;

    private int number;

    private LocalDate enrollmentDate;

    public void addEnrollmentSubject(EnrollmentSubjectUpdateDTO enrollmentSubject) {
        enrollmentSubject.setEnrollmentNumber(this.number);
        this.enrollmentSubjects.add(enrollmentSubject);
    }

    public void removeEnrollmentSubject(EnrollmentSubjectUpdateDTO enrollmentSubject) {
        this.enrollmentSubjects.remove(enrollmentSubject);
    }

    public void removeEnrollmentSubject(String subjectCode) {
        this.enrollmentSubjects.removeIf(s -> s.getSubjectCode().equalsIgnoreCase(subjectCode));
    }

}
