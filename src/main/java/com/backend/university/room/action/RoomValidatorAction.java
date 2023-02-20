package com.backend.university.room.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class RoomValidatorAction {

    private final RoomRepository repository;

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(format("There is already a room named \"%s\".", name));
        }
    }

}
