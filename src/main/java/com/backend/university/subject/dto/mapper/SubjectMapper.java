package com.backend.university.subject.dto.mapper;

import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.enrollmentsubject.dto.EnrollmentSubjectOutputDTO;
import com.backend.university.enrollmentsubject.dto.mapper.EnrollmentSubjectMapper;
import com.backend.university.professor.service.ProfessorService;
import com.backend.university.room.service.RoomService;
import com.backend.university.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;
import static com.backend.university.common.utils.ScheduleUtils.toPairs;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectMapper {

    private final SubjectService subjectService;

    private final ProfessorService professorService;

    private final RoomService roomService;

    private final EnrollmentSubjectMapper enrollmentSubjectMapper;

    public Subject inputToEntity(SubjectInputDTO input) {
        Subject subject = new Subject();
        subject.setCode(input.getCode());
        subject.setName(input.getName());
        subject.setStudyLoad(input.getStudyLoad());
        subject.setVacancies(input.getVacancies());
        subject.setRoom(roomService.findEntityByName(input.getRoom()));
        subject.setProfessor(professorService.findEntityByCpf(input.getProfessorCpf()));
        return subject;
    }

    public Subject updateToEntity(SubjectUpdateDTO update) {
        Subject subject = subjectService.findEntityById(update.getId());
        if (!subject.getRoom().getName().equalsIgnoreCase(update.getRoom())) {
            subject.setRoom(roomService.findEntityByName(update.getRoom()));
        }
        if (!subject.getProfessor().getCpf().equalsIgnoreCase(update.getProfessorCpf())) {
            subject.setProfessor(professorService.findEntityByCpf(update.getProfessorCpf()));
        }
        setIfNotNull(update.getCode(), subject::setCode);
        setIfNotNull(update.getName(), subject::setName);
        setIfNotNull(update.getStudyLoad(), subject::setStudyLoad);
        setIfNotNull(update.getVacancies(), subject::setVacancies);
        return subject;
    }

    public SubjectOutputDTO entityToOutput(Subject subject) {
        Set<EnrollmentSubjectOutputDTO> subjects =
                subject.getEnrollmentSubjects().stream()
                .map(enrollmentSubjectMapper::entityToOutput)
                .collect(Collectors.toSet());

        return SubjectOutputDTO.builder()
                .id(subject.getId())
                .code(subject.getCode())
                .name(subject.getName())
                .studyLoad(subject.getStudyLoad())
                .vacancies(subject.getVacancies())
                .schedule(toPairs(subject.getSchedule()))
                .room(subject.getRoom().getName())
                .professorCpf(subject.getProfessor().getCpf())
                .enrollmentSubjects(subjects)
                .build();
    }

}
