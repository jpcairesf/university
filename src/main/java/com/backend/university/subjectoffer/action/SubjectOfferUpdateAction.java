package com.backend.university.subjectoffer.action;

import com.backend.university.room.action.RoomRelatedAction;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferUpdateDTO;
import com.backend.university.subjectoffer.enumx.AmPmEnum;
import com.backend.university.subjectoffer.exception.SubjectOfferExceptionSupplier;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class SubjectOfferUpdateAction {

    private final SubjectOfferRepository repository;

    private final SubjectOfferValidatorAction validatorAction;

    private final RoomRelatedAction roomRelatedAction;

    public SubjectOffer update(SubjectOfferUpdateDTO update) {
        validatorAction.validateDayOfWeek(update.getDayOfWeek());
        validatorAction.validateAmPm(update.getAmPm());

        SubjectOffer subjectOffer = this.findEntityById(update.getId());

        subjectOffer.setRoom(roomRelatedAction.findEntityByName(update.getRoomName()));
        subjectOffer.setStartTime(update.getStartTime());
        subjectOffer.setDayOfWeek(DayOfWeek.valueOf(update.getDayOfWeek()));
        subjectOffer.setAmPm(AmPmEnum.valueOf(update.getAmPm()));
        subjectOffer.setDurationMin(update.getDurationMin());
        subjectOffer.setVacancies(update.getVacancies());

        return repository.save(subjectOffer);
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectOfferExceptionSupplier.notFoundById(id));
    }

}
