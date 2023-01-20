package com.backend.university.room.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomOutputDTO {

    private Long id;

    private String name;

    private String location;

}
