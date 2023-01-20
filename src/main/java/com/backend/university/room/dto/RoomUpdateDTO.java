package com.backend.university.room.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomUpdateDTO {

    private Long id;

    private String name;

    private String location;

}
