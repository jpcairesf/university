package com.backend.university.subjectoffer.dto.mapper;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.TextStyle;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectOfferMapper {

    public static SubjectOfferOutputDTO entityToOutput(SubjectOffer subjectOffer) {
        return SubjectOfferOutputDTO.builder()
                .id(subjectOffer.getId())
                .courseName(subjectOffer.getCourse().getName())
                .subjectCode(subjectOffer.getSubject().getCode())
                .professorName(subjectOffer.getProfessor().getName())
                .roomName(subjectOffer.getRoom().getName())
                .startTime(subjectOffer.getStartTime())
                .dayOfWeek(subjectOffer.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                .amPm(subjectOffer.getAmPm().getDescription())
                .semester(subjectOffer.getSemester())
                .classNumber(subjectOffer.getClassNumber())
                .durationMin(subjectOffer.getDurationMin())
                .vacancies(subjectOffer.getVacancies())
                .build();
    }

}
