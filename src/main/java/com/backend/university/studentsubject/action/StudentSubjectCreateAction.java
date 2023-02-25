package com.backend.university.studentsubject.action;

import com.backend.university.student.action.StudentRelatedAction;
import com.backend.university.student.domain.Student;
import com.backend.university.studentsubject.domain.StudentSubject;
import com.backend.university.studentsubject.domain.id.StudentSubjectId;
import com.backend.university.studentsubject.dto.StudentSubjectInputDTO;
import com.backend.university.studentsubject.repository.StudentSubjectRepository;
import com.backend.university.subjectoffer.action.SubjectOfferRelatedAction;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentSubjectCreateAction {

    private final StudentSubjectRepository repository;

    private final StudentSubjectValidatorAction validatorAction;

    private final StudentRelatedAction studentRelatedAction;

    private final SubjectOfferRelatedAction subjectOfferRelatedAction;

    public StudentSubject create(StudentSubjectInputDTO input) {
        validatorAction.validateExistsByEnrollmentSubjectSemesterClass(
                input.getEnrollmentNumber(),
                input.getSubjectCode(),
                input.getSemester(),
                input.getClassNumber());

        StudentSubject studentSubject = new StudentSubject();
        Student student = studentRelatedAction.findEntityByNumber(input.getEnrollmentNumber());

        SubjectOffer subjectOffer = subjectOfferRelatedAction.findIdByCourseSubjectSemesterClass(
                student.getCourse().getId(),
                input.getSubjectCode(),
                input.getSemester(),
                input.getClassNumber());

        validatorAction.validateVacanciesOffer(subjectOffer);

        studentSubject.setId(new StudentSubjectId(
                student.getId(),
                subjectOffer.getId()));
        studentSubject.setSemester(input.getSemester());
        studentSubject.setStudent(student);
        studentSubject.setSubjectOffer(subjectOffer);

        return repository.save(studentSubject);
    }

}
