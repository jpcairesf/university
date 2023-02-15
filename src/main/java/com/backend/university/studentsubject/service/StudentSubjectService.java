package com.backend.university.studentsubject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.student.domain.Student;
import com.backend.university.student.dto.StudentOutputDTO;
import com.backend.university.student.service.StudentService;
import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectInputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectUpdateDTO;
import com.backend.university.studentsubject.dto.mapper.StudentSubjectMapper;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.service.SubjectOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentSubjectService {

    private final StudentSubjectRepository repository;

    private final StudentService studentService;

    private final SubjectOfferService subjectOfferService;

    @Transactional
    public StudentSubjectOutputDTO findById(StudentSubjectId id) {
        return StudentSubjectMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional
    public List<StudentSubjectOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(StudentSubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentSubjectOutputDTO create(StudentSubjectInputDTO input) {
        this.validateExistsByEnrollmentSubjectSemesterClass(
                input.getEnrollmentNumber(),
                input.getSubjectCode(),
                input.getSemester(),
                input.getClassNumber());

        StudentSubject studentSubject = new StudentSubject();
        Student student = studentService.findEntityByNumber(input.getEnrollmentNumber());

        SubjectOffer subjectOffer = subjectOfferService.findIdByCourseSubjectSemesterClass(
                student.getCourse().getId(),
                input.getSubjectCode(),
                input.getSemester(),
                input.getClassNumber());

        this.validateVacanciesOffer(subjectOffer);

        studentSubject.setId(new StudentSubjectId(
                student.getId(),
                subjectOffer.getId()));
        studentSubject.setSemester(input.getSemester());
        studentSubject.setStudent(student);
        studentSubject.setSubjectOffer(subjectOffer);

        repository.save(studentSubject);
        return StudentSubjectMapper.entityToOutput(studentSubject);
    }

    @Transactional
    public StudentSubjectOutputDTO update(StudentSubjectUpdateDTO update) {
        StudentSubject studentSubject =
                this.findEagerByEnrollmentSubjectSemesterClass(
                        update.getEnrollmentNumber(),
                        update.getSubjectCode(),
                        update.getSemester(),
                        update.getClassNumber());
        studentSubject.setSemester(update.getSemester());
        studentSubject.setGrade(update.getGrade());

        repository.save(studentSubject);
        return StudentSubjectMapper.entityToOutput(studentSubject);
    }

    @Transactional
    public void delete(StudentSubjectId id) {
        repository.delete(findEntityById(id));
    }

    public void validateVacanciesOffer(SubjectOffer subjectOffer) {
        if (repository.countBySubjectOffer(subjectOffer) == subjectOffer.getVacancies()) {
            throw new BusinessException("There is no available vacancies");
        }
    }

    private StudentSubject findEagerByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        return repository.findEagerByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\" in class with number \"%s\".", enrollmentNumber, subjectCode, semester, classNumber)));
    }

    private void validateExistsByEnrollmentSubjectSemesterClass(int enrollmentNumber, String subjectCode, int semester, int classNumber) {
        if (repository.findEagerByEnrollmentSubjectSemesterClass(enrollmentNumber, subjectCode, semester, classNumber).isPresent()) {
            throw new BusinessException(format("There is already an enrollment number \"%s\" in the subject with code \"%s\" in semester \"%s\" in class with number \"%s\".", enrollmentNumber, subjectCode, semester, classNumber));
        }
    }

    private StudentSubject findEntityById(StudentSubjectId id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no enrollment subject with ID \"%s\".", id.toString())));
    }

}
