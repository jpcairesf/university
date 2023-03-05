package com.backend.university.room.service;

import com.backend.university.room.action.RoomCreateAction;
import com.backend.university.room.action.RoomDeleteAction;
import com.backend.university.room.action.RoomGetAction;
import com.backend.university.room.action.RoomUpdateAction;
import com.backend.university.room.dto.RoomInputDTO;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.RoomUpdateDTO;
import com.backend.university.room.dto.mapper.RoomMapper;
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
        return RoomMapper.entityToOutput(getAction.findById(id));
    }

    @Transactional(readOnly = true)
    public List<RoomOutputDTO> findAll() {
        return getAction.findAll();
    }

    @Transactional
    public RoomOutputDTO create(RoomInputDTO input) {
        return RoomMapper.entityToOutput(createAction.create(input));
    }

    @Transactional
    public RoomOutputDTO update(RoomUpdateDTO update) {
        return RoomMapper.entityToOutput(updateAction.update(update));
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
