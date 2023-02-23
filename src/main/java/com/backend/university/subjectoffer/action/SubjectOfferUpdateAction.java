package com.backend.university.subjectoffer.action;

import com.backend.university.room.service.RoomService;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferUpdateDTO;
import com.backend.university.subjectoffer.dto.mapper.SubjectOfferMapper;
import com.backend.university.subjectoffer.enumx.AmPmEnum;
import com.backend.university.subjectoffer.exception.SubjectOfferExceptionSupplier;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class SubjectOfferUpdateAction {

    private final SubjectOfferRepository repository;

    private final SubjectOfferValidatorAction validatorAction;

    private final RoomService roomService;

    @Transactional
    public SubjectOfferOutputDTO update(SubjectOfferUpdateDTO update) {
        validatorAction.validateDayOfWeek(update.getDayOfWeek());
        validatorAction.validateAmPm(update.getAmPm());

        SubjectOffer subjectOffer = this.findEntityById(update.getId());

        subjectOffer.setRoom(roomService.findEntityByName(update.getRoomName()));
        subjectOffer.setStartTime(update.getStartTime());
        subjectOffer.setDayOfWeek(DayOfWeek.valueOf(update.getDayOfWeek()));
        subjectOffer.setAmPm(AmPmEnum.valueOf(update.getAmPm()));
        subjectOffer.setDurationMin(update.getDurationMin());
        subjectOffer.setVacancies(update.getVacancies());

        repository.save(subjectOffer);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectOfferExceptionSupplier.notFoundById(id));
    }

}
