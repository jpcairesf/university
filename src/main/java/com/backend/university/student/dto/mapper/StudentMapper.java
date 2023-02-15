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
                student.getStudentSubjects().stream()
                .map(StudentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());

        return StudentOutputDTO.builder()
                .id(student.getId())
                .courseName(student.getCourse().getName())
                .enrollmentNumber(student.getEnrollmentNumber())
                .enrollmentDate(student.getEnrollmentDate())
                .cpf(student.getCpf())
                .name(student.getName())
                .email(student.getEmail())
                .studentSubjects(subjects)
                .build();
    }

}
