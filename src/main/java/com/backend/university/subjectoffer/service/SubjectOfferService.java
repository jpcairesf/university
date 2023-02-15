package com.backend.university.subjectoffer.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.service.CourseService;
import com.backend.university.room.service.RoomService;
import com.backend.university.subject.service.SubjectService;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferInputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferUpdateDTO;
import com.backend.university.subjectoffer.dto.mapper.SubjectOfferMapper;
import com.backend.university.subjectoffer.enumx.AmPm;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectOfferService {

    private final SubjectOfferRepository repository;

    private final CourseService courseService;

    private final SubjectService subjectService;

    private final RoomService roomService;

    public Long findIdByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber) {
        return repository.findIdByCourseSubjectSemesterClass(courseName, subjectCode, semester, classNumber)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer for course with name \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".", courseName, subjectCode, semester, classNumber)));
    }

    public SubjectOfferOutputDTO findById(Long id) {
        SubjectOffer subjectOffer = this.findEntityById(id);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

    public List<SubjectOfferOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(SubjectOfferMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    public SubjectOfferOutputDTO create(SubjectOfferInputDTO input) {
        this.validateExistsByCourseSubjectSemesterClass(input.getCourseName(), input.getSubjectCode(), input.getSemester(), input.getClassNumber());
        this.validateDayOfWeek(input.getDayOfWeek());
        this.validateAmPm(input.getAmPm());

        SubjectOffer subjectOffer = new SubjectOffer();
        subjectOffer.setCourse(courseService.findEntityByName(input.getCourseName()));
        subjectOffer.setSubject(subjectService.findEntityByCode(input.getSubjectCode()));
        subjectOffer.setRoom(roomService.findEntityByName(input.getRoomName()));
        subjectOffer.setStartTime(input.getStartTime());
        subjectOffer.setDayOfWeek(input.getDayOfWeek());
        subjectOffer.setAmPm(input.getAmPm());
        subjectOffer.setSemester(input.getSemester());
        subjectOffer.setClassNumber(input.getClassNumber());
        subjectOffer.setDurationMin(input.getDurationMin());
        subjectOffer.setVacancies(input.getVacancies());

        repository.save(subjectOffer);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

    private void validateAmPm(String amPm) {
        try {
            AmPm.valueOf(amPm);
        }
        catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid AM/PM value.");
        }
    }

    private void validateDayOfWeek(String dayOfWeek) {
        try {
            DayOfWeek.valueOf(dayOfWeek);
        }
        catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid day of week.");
        }
    }

    private void validateExistsByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber) {
        if (repository.existsByCourseNameAndSubjectCodeAndSemesterAndClassNumber(courseName, subjectCode, semester, classNumber)) {
            throw new BusinessException(format("There is already a subject offer for offer for subject course with name \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".", courseName, subjectCode, semester, classNumber));
        }
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer with ID \"%s\".", id)));
    }

    public SubjectOfferOutputDTO update(SubjectOfferUpdateDTO update) {
        this.validateDayOfWeek(update.getDayOfWeek());
        this.validateAmPm(update.getAmPm());

        SubjectOffer subjectOffer = this.findEntityById(update.getId());

        subjectOffer.setRoom(roomService.findEntityByName(update.getRoomName()));
        subjectOffer.setStartTime(update.getStartTime());
        subjectOffer.setDayOfWeek(update.getDayOfWeek());
        subjectOffer.setAmPm(update.getAmPm());
        subjectOffer.setDurationMin(update.getDurationMin());
        subjectOffer.setVacancies(update.getVacancies());

        repository.save(subjectOffer);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }
}
