package com.backend.university.subjectoffer.service;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.mapper.SubjectOfferMapper;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectOfferService {

    private final SubjectOfferRepository repository;

    public Long findIdByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber) {
        return repository.findIdByCourseSubjectSemesterClass(courseName, subjectCode, semester, classNumber)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer for course with name \"%s\" in subject with code \"%s\" in semester \"%s\" in class of number \"%s\".", courseName, subjectCode, semester, classNumber)));
    }

    public SubjectOfferOutputDTO findById(Long id) {
        SubjectOffer subjectOffer = this.findEntityById(id);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer with ID \"%s\".", id)));
    }
}
