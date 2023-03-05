package com.backend.university.course.controller;

import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.CourseUpdateDTO;
import com.backend.university.course.service.CourseService;
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
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping("/{id}")
    public ResponseEntity<CourseOutputDTO> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<CourseOutputDTO> create(@RequestBody CourseInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<CourseOutputDTO> update(@RequestBody CourseUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return status(HttpStatus.OK).build();
    }

}
