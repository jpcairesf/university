package com.backend.university.room.dto.mapper;

import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomInputDTO;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.RoomUpdateDTO;
import com.backend.university.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomMapper {

    private final RoomService roomService;

    public Room inputToEntity(RoomInputDTO input) {
        Room room = new Room();
        room.setName(input.getName());
        room.setLocation(input.getLocation());
        return room;
    }

    public Room updateToEntity(RoomUpdateDTO update) {
        Room room = roomService.findEntityById(update.getId());
        setIfNotNull(update.getName(), room::setName);
        setIfNotNull(update.getLocation(), room::setLocation);
        return room;
    }

    public RoomOutputDTO entityToOutput(Room room) {
        return RoomOutputDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .location(room.getLocation())
                .build();
    }

}
