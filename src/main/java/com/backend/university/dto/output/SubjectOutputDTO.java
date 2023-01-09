package com.backend.university.dto.output;

import com.backend.university.domain.enumx.SubjectSchedule;
import com.backend.university.dto.update.EnrollmentSubjectUpdateDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.List;

@Data
@Builder
public class SubjectOutputDTO {

    private Long id;

    private String code;

    private String professorCpf;

    private String room;

    private List<EnrollmentSubjectUpdateDTO> enrollmentSubjects;

    private List<Pair<DayOfWeek, SubjectSchedule>> schedule;

    private int studyLoad;

    private int vacancies;

}
