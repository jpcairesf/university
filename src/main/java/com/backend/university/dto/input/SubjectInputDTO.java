package com.backend.university.dto.input;

import com.backend.university.domain.enumx.SubjectSchedule;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.List;

@Data
@Builder
public class SubjectInputDTO {

    private String name;

    private String code;

    private String professorCpf;

    private String room;

    private List<Pair<DayOfWeek, SubjectSchedule>> schedule;

    private int studyLoad;

    private int vacancies;

}
