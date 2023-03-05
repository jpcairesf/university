package com.backend.university.room.action;

import com.backend.university.room.domain.Room;
import com.backend.university.room.exception.RoomExceptionSupplier;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomDeleteAction {

    private final RoomRepository repository;

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Room findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(RoomExceptionSupplier.notFoundById(id));
    }

}
