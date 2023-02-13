package com.backend.university.student.dto.mapper;

import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentMapper {

    public static StudentOutputDTO entityToOutput(Student student) {
        return StudentOutputDTO.builder()
                .id(student.getId())
                .courseName(student.getCourse().getName())
                .enrollmentNumber(student.getEnrollmentNumber())
                .enrollmentDate(student.getEnrollmentDate())
                .cpf(student.getCpf())
                .name(student.getName())
                .email(student.getEmail())
                .build();
    }

}
