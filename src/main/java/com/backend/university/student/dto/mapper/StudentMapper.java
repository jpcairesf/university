package com.backend.university.student.dto.mapper;

import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.mapper.StudentSubjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentMapper {

    public static StudentOutputDTO entityToOutput(Student student) {
        List<StudentSubjectOutputDTO> subjects =
                student.getEnrollmentSubjects().stream()
                .map(StudentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return StudentOutputDTO.builder()
                .id(student.getId())
                .studentCpf(student.getStudent().getCpf())
                .course(student.getCourse().getName())
                .enrollmentSubjects(subjects)
                .number(student.getNumber())
                .enrollmentDate(student.getEnrollmentDate())
                .build();
    }

}
