package com.backend.university.student.dto.mapper;

import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.EnrollmentOutputDTO;
import com.backend.university.studentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.mapper.EnrollmentSubjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentMapper {

    public static EnrollmentOutputDTO entityToOutput(Student student) {
        List<EnrollmentSubjectOutputDTO> subjects =
                student.getEnrollmentSubjects().stream()
                .map(EnrollmentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return EnrollmentOutputDTO.builder()
                .id(student.getId())
                .studentCpf(student.getStudent().getCpf())
                .course(student.getCourse().getName())
                .enrollmentSubjects(subjects)
                .number(student.getNumber())
                .enrollmentDate(student.getEnrollmentDate())
                .build();
    }

}
