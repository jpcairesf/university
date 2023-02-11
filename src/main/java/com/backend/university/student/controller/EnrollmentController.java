package com.backend.university.student.controller;

import com.backend.university.student.dto.EnrollmentInputDTO;
import com.backend.university.student.dto.EnrollmentOutputDTO;
import com.backend.university.student.dto.EnrollmentUpdateDTO;
import com.backend.university.student.service.EnrollmentService;
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
public class EnrollmentController {

    private final EnrollmentService service;

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentOutputDTO> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<EnrollmentOutputDTO> create(@RequestBody EnrollmentInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<EnrollmentOutputDTO> update(@RequestBody EnrollmentUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return status(HttpStatus.OK).build();
    }

}
