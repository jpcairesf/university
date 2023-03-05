package com.backend.university.subjectoffer.action;

import com.backend.university.course.action.CourseRelatedAction;
import com.backend.university.professor.action.ProfessorRelatedAction;
import com.backend.university.room.action.RoomRelatedAction;
import com.backend.university.subject.action.SubjectRelatedAction;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferInputDTO;
import com.backend.university.subjectoffer.enumx.AmPmEnum;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class SubjectOfferCreateAction {

    private final SubjectOfferRepository repository;

    private final SubjectOfferValidatorAction validatorAction;

    private final CourseRelatedAction courseRelatedAction;

    private final SubjectRelatedAction subjectRelatedAction;

    private final ProfessorRelatedAction professorRelatedAction;

    private final RoomRelatedAction roomRelatedAction;

    public SubjectOffer create(SubjectOfferInputDTO input) {
        validatorAction.validateExistsByCourseSubjectSemesterClass(input.getCourseName(), input.getSubjectCode(), input.getSemester(), input.getClassNumber());
        validatorAction.validateSemester(input.getSemester());
        validatorAction.validateDayOfWeek(input.getDayOfWeek());
        validatorAction.validateAmPm(input.getAmPm());

        SubjectOffer subjectOffer = new SubjectOffer();
        subjectOffer.setCourse(courseRelatedAction.findEntityByName(input.getCourseName()));
        subjectOffer.setSubject(subjectRelatedAction.findEntityByCode(input.getSubjectCode()));
        subjectOffer.setProfessor(professorRelatedAction.findEntityByCpf(input.getProfessorCpf()));
        subjectOffer.setRoom(roomRelatedAction.findEntityByName(input.getRoomName()));
        subjectOffer.setStartTime(input.getStartTime());
        subjectOffer.setDayOfWeek(DayOfWeek.valueOf(input.getDayOfWeek()));
        subjectOffer.setAmPm(AmPmEnum.valueOf(input.getAmPm()));
        subjectOffer.setSemester(input.getSemester());
        subjectOffer.setClassNumber(input.getClassNumber());
        subjectOffer.setDurationMin(input.getDurationMin());
        subjectOffer.setVacancies(input.getVacancies());

        return repository.save(subjectOffer);
    }

}
