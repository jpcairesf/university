package com.backend.university.room.action;

import com.backend.university.room.domain.Room;
import com.backend.university.room.exception.RoomExceptionSupplier;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomRelatedAction {

    private final RoomRepository repository;

    public Room findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(RoomExceptionSupplier.notFoundByName(name));
    }

}
