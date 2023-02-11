package com.backend.university.studentsubject.controller;

import com.backend.university.studentsubject.dto.EnrollmentSubjectInputDTO;
import com.backend.university.studentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.EnrollmentSubjectUpdateDTO;
import com.backend.university.studentsubject.service.EnrollmentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/enrollment-subject")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrollmentSubjectController {

    private final EnrollmentSubjectService service;

    @GetMapping
    public ResponseEntity<List<EnrollmentSubjectOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<EnrollmentSubjectOutputDTO> create(@RequestBody EnrollmentSubjectInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<EnrollmentSubjectOutputDTO> update(@RequestBody EnrollmentSubjectUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

}
