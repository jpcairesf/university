package com.backend.university.subjectoffer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class SubjectOfferOutputDTO {

    private Long id;

    private String courseName;

    private String subjectCode;

    private String roomName;

    private LocalTime startTime;

    private String dayOfWeek;

    private String amPm;

    private int semester;

    private int classNumber;

    private int durationMin;

    private int vacancies;

}
