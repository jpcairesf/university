package com.backend.university.room.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.room.domain.Room;
import com.backend.university.room.dto.RoomInputDTO;
import com.backend.university.room.dto.RoomOutputDTO;
import com.backend.university.room.dto.RoomUpdateDTO;
import com.backend.university.room.dto.mapper.RoomMapper;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private final RoomRepository repository;

    private final RoomMapper mapper;

    @Transactional
    public RoomOutputDTO create(RoomInputDTO input) {
        this.validateExistsByName(input.getName());
        Room room = mapper.inputToEntity(input);
        repository.save(room);
        return mapper.entityToOutput(room);
    }

    @Transactional
    public RoomOutputDTO update(RoomUpdateDTO update) {
        Room room = this.findEntityByName(update.getName());

        if (!update.getName().equalsIgnoreCase(room.getName())) {
            this.validateExistsByName(update.getName());
            room.setName(update.getName());
        }
        room.setLocation(update.getLocation());
        repository.save(room);
        return mapper.entityToOutput(room);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Room findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no room with ID \"%s\".", id)));
    }

    public Room findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no room named \"%s\".", name)));
    }

    private void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a room named \"%s\".", name));
        }
    }

}
