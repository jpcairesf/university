package com.backend.university.secretary.controller;

import com.backend.university.secretary.dto.SecretaryInputDTO;
import com.backend.university.secretary.dto.SecretaryOutputDTO;
import com.backend.university.secretary.dto.SecretaryUpdateDTO;
import com.backend.university.secretary.service.SecretaryService;
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
@RequestMapping("/secretary")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecretaryController {

    private final SecretaryService service;

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryOutputDTO> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SecretaryOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<SecretaryOutputDTO> create(@RequestBody SecretaryInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<SecretaryOutputDTO> update(@RequestBody SecretaryUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return status(HttpStatus.OK).build();
    }

}
