package com.backend.university.room.dto.mapper;

import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomOutputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomMapper {

    public static RoomOutputDTO entityToOutput(Room room) {
        return RoomOutputDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .location(room.getLocation())
                .build();
    }

}
