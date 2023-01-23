package com.backend.university.subject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.professor.service.ProfessorService;
import com.backend.university.room.service.RoomService;
import com.backend.university.subject.domain.Subject;
import com.backend.university.subject.dto.SubjectInputDTO;
import com.backend.university.subject.dto.SubjectOutputDTO;
import com.backend.university.subject.dto.SubjectUpdateDTO;
import com.backend.university.subject.dto.mapper.SubjectMapper;
import com.backend.university.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backend.university.common.utils.MapperUtils.setIfNotNull;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectService {

    private final SubjectRepository repository;

    private final SubjectMapper mapper;

    private final RoomService roomService;

    private final ProfessorService professorService;

    @Transactional
    public SubjectOutputDTO create(SubjectInputDTO input) {
        this.validateExistsByCode(input.getCode());
        Subject subject = mapper.inputToEntity(input);
        repository.save(subject);
        return mapper.entityToOutput(subject);
    }

    //TODO Create find methods

    @Transactional
    public SubjectOutputDTO update(SubjectUpdateDTO update) {
        Subject subject = this.findEntityById(update.getId());

        if (!update.getCode().equalsIgnoreCase(subject.getCode())) {
            this.validateExistsByCode(update.getCode());
            subject.setCode(update.getCode());
        }
        if (!update.getRoom().equalsIgnoreCase(subject.getRoom().getName())) {
            subject.setRoom(roomService.findEntityByName(update.getRoom()));
        }
        if (!update.getProfessorCpf().equalsIgnoreCase(subject.getProfessor().getCpf())) {
            subject.setProfessor(professorService.findEntityByCpf(update.getProfessorCpf()));
        }
        subject.setName(update.getName());
        subject.setStudyLoad(update.getStudyLoad());
        subject.setVacancies(update.getVacancies());
        repository.save(subject);
        return mapper.entityToOutput(subject);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    public Long findIdByCode(String code) {
        return repository.findIdByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

    public Subject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no subject with ID \"%s\".", id)));
    }

    public Subject findEntityByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new BusinessException(format("There is no subject with code \"%s\".", code)));
    }

    private void validateExistsByCode(String code) {
        if (repository.existsByCode(code)) {
            throw new BusinessException(format("There is already a subject with code \"%s\".", code));
        }
    }

}
