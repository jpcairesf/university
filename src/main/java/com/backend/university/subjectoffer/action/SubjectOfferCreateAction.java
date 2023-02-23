package com.backend.university.subjectoffer.action;

import com.backend.university.course.service.CourseService;
import com.backend.university.professor.service.ProfessorService;
import com.backend.university.room.service.RoomService;
import com.backend.university.subject.service.SubjectService;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferInputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.mapper.SubjectOfferMapper;
import com.backend.university.subjectoffer.enumx.AmPmEnum;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;

@Component
@RequiredArgsConstructor
public class SubjectOfferCreateAction {

    private final SubjectOfferRepository repository;

    private final SubjectOfferValidatorAction validatorAction;

    private final CourseService courseService;

    private final SubjectService subjectService;

    private final ProfessorService professorService;

    private final RoomService roomService;

    @Transactional
    public SubjectOfferOutputDTO create(SubjectOfferInputDTO input) {
        validatorAction.validateSemester(input.getSemester());
        validatorAction.validateDayOfWeek(input.getDayOfWeek());
        validatorAction.validateAmPm(input.getAmPm());
        validatorAction.validateExistsByCourseSubjectSemesterClass(input.getCourseName(), input.getSubjectCode(), input.getSemester(), input.getClassNumber());

        SubjectOffer subjectOffer = new SubjectOffer();
        subjectOffer.setCourse(courseService.findEntityByName(input.getCourseName()));
        subjectOffer.setSubject(subjectService.findEntityByCode(input.getSubjectCode()));
        subjectOffer.setProfessor(professorService.findEntityByCpf(input.getProfessorCpf()));
        subjectOffer.setRoom(roomService.findEntityByName(input.getRoomName()));
        subjectOffer.setStartTime(input.getStartTime());
        subjectOffer.setDayOfWeek(DayOfWeek.valueOf(input.getDayOfWeek()));
        subjectOffer.setAmPm(AmPmEnum.valueOf(input.getAmPm()));
        subjectOffer.setSemester(input.getSemester());
        subjectOffer.setClassNumber(input.getClassNumber());
        subjectOffer.setDurationMin(input.getDurationMin());
        subjectOffer.setVacancies(input.getVacancies());

        repository.save(subjectOffer);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

}
