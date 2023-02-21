package com.backend.university.room.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.room.exception.RoomExceptionMessages;
import com.backend.university.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomValidatorAction {

    private final RoomRepository repository;

    public void validateExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(RoomExceptionMessages.existsByName(name));
        }
    }

}
