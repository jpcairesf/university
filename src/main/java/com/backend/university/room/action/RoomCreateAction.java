package com.backend.university.room.action;

import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomInputDTO;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.mapper.RoomMapper;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RoomCreateAction {

    private final RoomRepository repository;

    private final RoomValidatorAction validatorAction;

    @Transactional
    public RoomOutputDTO create(RoomInputDTO input) {
        validatorAction.validateExistsByName(input.getName());

        Room room = new Room();
        room.setName(input.getName());
        room.setLocation(input.getLocation());

        repository.save(room);
        return RoomMapper.entityToOutput(room);
    }

}
