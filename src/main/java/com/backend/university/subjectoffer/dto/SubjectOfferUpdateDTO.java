package com.backend.university.subjectoffer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class SubjectOfferUpdateDTO {

    private Long id;

    private String roomName;

    private String professorCpf;

    private LocalTime startTime;

    private String dayOfWeek;

    private String amPm;

    private int durationMin;

    private int vacancies;

}
