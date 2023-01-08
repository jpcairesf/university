package com.backend.university.dto.update;

import com.backend.university.domain.enumx.SubjectSchedule;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.List;

@Data
@Builder
public class SubjectUpdateDTO {

    private Long id;

    private String code;

    private String professorCpf;

    private String room;

    private List<EnrollmentSubjectUpdateDTO> enrollmentSubjects;

    private List<Pair<DayOfWeek, SubjectSchedule>> schedule;

    private int studyLoad;

    private int vacancies;

    public void addEnrollmentSubject(EnrollmentSubjectUpdateDTO enrollmentSubject) {
        enrollmentSubject.setSubjectCode(this.code);
        this.enrollmentSubjects.add(enrollmentSubject);
    }

    public void removeEnrollmenteSubject(EnrollmentSubjectUpdateDTO enrollmentSubject) {
        this.enrollmentSubjects.remove(enrollmentSubject);
    }

    public void removeEnrollmenteSubject(String subjectCode) {
        this.enrollmentSubjects.removeIf(s -> s.getSubjectCode().equalsIgnoreCase(subjectCode));
    }

}
