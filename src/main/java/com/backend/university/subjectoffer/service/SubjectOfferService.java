package com.backend.university.subjectoffer.service;

import com.backend.university.subjectoffer.action.SubjectOfferCreateAction;
import com.backend.university.subjectoffer.action.SubjectOfferDeleteAction;
import com.backend.university.subjectoffer.action.SubjectOfferGetAction;
import com.backend.university.subjectoffer.action.SubjectOfferUpdateAction;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferInputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferUpdateDTO;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubjectOfferService {

    private final SubjectOfferRepository repository;

    private final SubjectOfferGetAction getAction;

    private final SubjectOfferCreateAction createAction;

    private final SubjectOfferUpdateAction updateAction;

    private final SubjectOfferDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public SubjectOfferOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<SubjectOfferOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    @Transactional
    public SubjectOfferOutputDTO create(SubjectOfferInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public SubjectOfferOutputDTO update(SubjectOfferUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

    public SubjectOffer findIdByCourseSubjectSemesterClass(Long courseId, String subjectCode, int semester, int classNumber) {
        return repository.findIdByCourseSubjectSemesterClass(courseId, subjectCode, semester, classNumber)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer for course with ID \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".", courseId, subjectCode, semester, classNumber)));
    }

}
