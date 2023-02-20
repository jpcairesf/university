package com.backend.university.room.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.mapper.RoomMapper;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class RoomGetAction {

    private final RoomRepository repository;

    @Transactional(readOnly = true)
    public RoomOutputDTO findById(Long id) {
        return RoomMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<RoomOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(RoomMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Room findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no room with ID \"%s\".", id)));
    }

}
