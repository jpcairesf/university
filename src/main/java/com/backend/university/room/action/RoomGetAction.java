package com.backend.university.room.action;

import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.mapper.RoomMapper;
import com.backend.university.room.exception.RoomExceptionSupplier;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoomGetAction {

    private final RoomRepository repository;

    public Room findById(Long id) {
        return repository.findById(id)
                .orElseThrow(RoomExceptionSupplier.notFoundById(id));
    }

    public List<RoomOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(RoomMapper::entityToOutput)
                .collect(Collectors.toList());
    }

}
