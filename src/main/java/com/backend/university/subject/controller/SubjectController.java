package com.backend.university.subject.controller;

import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.subject.service.SubjectService;
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
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;

    @GetMapping("/{id}")
    public ResponseEntity<SubjectOutputDTO> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubjectOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<SubjectOutputDTO> create(@RequestBody SubjectInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<SubjectOutputDTO> update(@RequestBody SubjectUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return status(HttpStatus.OK).build();
    }

}
