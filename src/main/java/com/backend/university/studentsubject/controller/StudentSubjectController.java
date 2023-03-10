package com.backend.university.studentsubject.controller;

import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectInputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectUpdateDTO;
import com.backend.university.studentsubject.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/student-subject")
@RequiredArgsConstructor
public class StudentSubjectController {

    private final StudentSubjectService service;

    @GetMapping
    public ResponseEntity<List<StudentSubjectOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<StudentSubjectOutputDTO> create(@RequestBody StudentSubjectInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<StudentSubjectOutputDTO> update(@RequestBody StudentSubjectUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{studentId}/{subjectOfferId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId, @PathVariable Long subjectOfferId) {
        service.delete(new StudentSubjectId(studentId, subjectOfferId));
        return status(HttpStatus.OK).build();
    }

}
