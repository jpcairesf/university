package com.backend.university.room.controller;

import com.backend.university.room.dto.RoomInputDTO;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.RoomUpdateDTO;
import com.backend.university.room.service.RoomService;
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
@RequestMapping("/room")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {
    
    private final RoomService service;

    @GetMapping("/{id}")
    public ResponseEntity<RoomOutputDTO> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoomOutputDTO>> findAll() {
        return status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<RoomOutputDTO> create(@RequestBody RoomInputDTO input) {
        return status(HttpStatus.CREATED).body(service.create(input));
    }

    @PutMapping
    public ResponseEntity<RoomOutputDTO> update(@RequestBody RoomUpdateDTO update) {
        return status(HttpStatus.OK).body(service.update(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return status(HttpStatus.OK).build();
    }
    
}
