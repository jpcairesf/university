package com.backend.university.subject.dto.mapper;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectOutputDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SubjectMapper {

    public static SubjectOutputDTO entityToOutput(Subject subject) {
        return SubjectOutputDTO.builder()
                .id(subject.getId())
                .code(subject.getCode())
                .name(subject.getName())
                .studyLoad(subject.getStudyLoad())
                .build();
    }

}
