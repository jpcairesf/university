package com.backend.university.room.service;

import com.backend.university.room.action.RoomCreateAction;
import com.backend.university.room.action.RoomDeleteAction;
import com.backend.university.room.action.RoomGetAction;
import com.backend.university.room.action.RoomUpdateAction;
import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomInputDTO;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.RoomUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomGetAction getAction;

    private final RoomCreateAction createAction;

    private final RoomUpdateAction updateAction;

    private final RoomDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public RoomOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<RoomOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public Room findEntityByName(String name) {
        return this.getAction.findEntityByName(name);
    }

    @Transactional
    public RoomOutputDTO create(RoomInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public RoomOutputDTO update(RoomUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

}
