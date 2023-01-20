package com.backend.university.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.domain.Room;
import com.backend.university.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private final RoomRepository repository;

    public Room findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no room with ID \"%s\".", id)));
    }

    public Room findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new BusinessException(format("There is no room named \"%s\".", name)));
    }

}
