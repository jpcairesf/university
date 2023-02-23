package com.backend.university.subjectoffer.service;

import com.backend.university.subjectoffer.action.SubjectOfferCreateAction;
import com.backend.university.subjectoffer.action.SubjectOfferDeleteAction;
import com.backend.university.subjectoffer.action.SubjectOfferGetAction;
import com.backend.university.subjectoffer.action.SubjectOfferUpdateAction;
import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferInputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.SubjectOfferUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectOfferService {

    private final SubjectOfferGetAction getAction;

    private final SubjectOfferCreateAction createAction;

    private final SubjectOfferUpdateAction updateAction;

    private final SubjectOfferDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public SubjectOfferOutputDTO findById(Long id) {
        return getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<SubjectOfferOutputDTO> findAll() {
        return getAction.findAll();
    }

    @Transactional
    public SubjectOffer findIdByCourseSubjectSemesterClass(Long courseId, String subjectCode, int semester, int classNumber) {
        return getAction.findIdByCourseSubjectSemesterClass(courseId, subjectCode, semester, classNumber);
    }

    @Transactional
    public SubjectOfferOutputDTO create(SubjectOfferInputDTO input) {
        return createAction.create(input);
    }

    @Transactional
    public SubjectOfferOutputDTO update(SubjectOfferUpdateDTO update) {
        return updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
