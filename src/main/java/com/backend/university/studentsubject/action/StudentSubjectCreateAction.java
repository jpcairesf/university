package com.backend.university.studentsubject.action;

import com.backend.university.student.domain.Student;
import com.backend.university.student.service.StudentService;
import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectInputDTO;
import com.backend.university.studentsubject.dto.StudentSubjectOutputDTO;
import com.backend.university.studentsubject.dto.mapper.StudentSubjectMapper;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.service.SubjectOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudentSubjectCreateAction {

    private final StudentSubjectRepository repository;

    private final StudentSubjectValidatorAction validatorAction;

    private final StudentService studentService;

    private final SubjectOfferService subjectOfferService;

    @Transactional
    public StudentSubjectOutputDTO create(StudentSubjectInputDTO input) {
        this.validatorAction.validateExistsByEnrollmentSubjectSemesterClass(
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

        this.validatorAction.validateVacanciesOffer(subjectOffer);

        studentSubject.setId(new StudentSubjectId(
                student.getId(),
                subjectOffer.getId()));
        studentSubject.setSemester(input.getSemester());
        studentSubject.setStudent(student);
        studentSubject.setSubjectOffer(subjectOffer);

        repository.save(studentSubject);
        return StudentSubjectMapper.entityToOutput(studentSubject);
    }

}
