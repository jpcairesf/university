package com.backend.university.department.controller;

import com.backend.university.department.dto.DepartmentInputDTO;
import com.backend.university.department.dto.DepartmentOutputDTO;
import com.backend.university.department.dto.DepartmentUpdateDTO;
import com.backend.university.department.service.DepartmentService;
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

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentOutputDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentOutputDTO> create(@RequestBody DepartmentInputDTO input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<DepartmentOutputDTO> update(@RequestBody DepartmentUpdateDTO update) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
