package com.backend.university.subjectoffer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class SubjectOfferInputDTO {

    private String courseName;

    private String subjectCode;

    private String roomName;

    private String professorCpf;

    private LocalTime startTime;

    private String dayOfWeek;

    private String amPm;

    private int semester;

    private int classNumber;

    private int durationMin;

    private int vacancies;

}
