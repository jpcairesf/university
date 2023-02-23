package com.backend.university.room.action;

import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.RoomUpdateDTO;
import com.backend.university.room.dto.mapper.RoomMapper;
import com.backend.university.room.exception.RoomExceptionSupplier;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RoomUpdateAction {

    private final RoomRepository repository;

    private final RoomValidatorAction validatorAction;

    @Transactional
    public RoomOutputDTO update(RoomUpdateDTO update) {
        Room room = this.findEntityById(update.getId());

        if (!update.getName().equalsIgnoreCase(room.getName())) {
            validatorAction.validateExistsByName(update.getName());
            room.setName(update.getName());
        }
        room.setLocation(update.getLocation());

        repository.save(room);
        return RoomMapper.entityToOutput(room);
    }

    private Room findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(RoomExceptionSupplier.notFoundById(id));
    }

}
