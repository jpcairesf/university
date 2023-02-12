package com.backend.university.student.controller;

import com.backend.university.student.dto.StudentInputDTO;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.dto.StudentUpdateDTO;
import com.backend.university.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/enrollment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final StudentService service;

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDTO> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<StudentOutputDTO> create(@RequestBody StudentInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<StudentOutputDTO> update(@RequestBody StudentUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return status(HttpStatus.OK).build();
    }

}
