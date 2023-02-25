package com.backend.university.subjectoffer.action;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.exception.SubjectOfferExceptionSupplier;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectOfferRelatedAction {

    private final SubjectOfferRepository repository;

    public SubjectOffer findIdByCourseSubjectSemesterClass(Long courseId, String subjectCode, int semester, int classNumber) {
        return repository.findIdByCourseSubjectSemesterClass(courseId, subjectCode, semester, classNumber)
                .orElseThrow(SubjectOfferExceptionSupplier.notFoundByCourseSubjectSemesterClass(courseId, subjectCode, semester, classNumber));
    }

}
